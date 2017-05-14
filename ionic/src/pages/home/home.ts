import { Component} from '@angular/core';
import { Geolocation } from '@ionic-native/geolocation';
import { LoadingController } from 'ionic-angular';
import { NavController, NavParams } from 'ionic-angular';
import { AboutPage} from '../about/about';
import { DetalleReserva } from '../detalle-reserva/detalle-reserva';
import {AgregarFavorito} from '../agregarfavorito/agregarfavorito';
import {InfoReserva} from '../inforeserva/inforeserva';
import { ModalController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  public   map;
  lati:any;
  longi:any;
  posicion:any;
  usuario:any;

  constructor(public loadingCtrl: LoadingController, private geolocation: Geolocation,
    public navCtrl: NavController, public navParams: NavParams,public storage: Storage,public modalCtrl: ModalController) {
      this.traerUsuario();
    }

    presentProfileModal(parqueadero) {
      let reservaModal = this.modalCtrl.create(DetalleReserva, { parqueadero: parqueadero});
      reservaModal.onDidDismiss(data => {
        console.log(data);
        if(data.idParqueadero!=='cancelar'){
          this.navCtrl.push(InfoReserva, {usuario:this.usuario,parqueadero:parqueadero});
        }
      });
      reservaModal.present();
    }

    ionViewDidLoad(){
      this.locateUser();
    }

    traerUsuario(){
      this.storage.get('usuario').then((val) => {
        console.log('en home ', val);
        this.usuario= val;
      }).catch((errr)=>{console.log('no deberia paar por ava')})
    }

    presentLoading(duracion) {
      let loader = this.loadingCtrl.create({
        content: "Cargando... ",
        duration: duracion
      });
      loader.present();
    }

    locateUser(){
      this.presentLoading(2000);
      this.geolocation.getCurrentPosition().then((pos) => {
        let latLng = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
        this.posicion=pos;
        this.map=this.initMap(pos);
        this.insertarParqueaderos(this.map, latLng );
        this.insertarBusqueda(this.map,this);
        this.map.panTo(latLng);
      });
    }

    //boton azul
    locateUserBoton(){
      console.log("boton azul");
      this.geolocation.getCurrentPosition().then((pos) => {
        this.posicion=pos;
        let latLng = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
        this.map.panTo(latLng);
        this.map.setZoom(16);
      }).catch((error)=>console.log("error en la geolocation"));
      console.log(this.posicion);
    }

    //boton favoritos
    favUserBoton(){
      let favoritoListaModal = this.modalCtrl.create(AboutPage, { favoritos:this.usuario.favoritos});
      favoritoListaModal.onDidDismiss(data => {
        console.log(data.coords);
        if(data.coords!==undefined){
          console.log("ir a lugar favorito");
          this.locateUserFavorito(data);
          this.traerUsuario();
        }
        else{
          console.log("no ir a favorito");
        }
      });
      favoritoListaModal.present();

    }


    locateUserFavorito(coordenadas){
      this.presentLoading(1000);
      let latLng = new google.maps.LatLng(coordenadas.coords.latitude,coordenadas.coords.longitude);
      console.log(latLng);
      this.map=this.initMap(coordenadas);
      this.insertarFavoritoMapa(this.map,latLng);
      this.insertarParqueaderos(this.map, latLng );
      this.insertarBusqueda(this.map,this);
      this.map.panTo(latLng);
      this.map.setZoom(16);
    }

    agregarFav(coord,namespace){
      let coords = {latitude:coord.lat,longitude:coord.lng};
      let favoritoModal = this.modalCtrl.create(AgregarFavorito, { nombre:namespace,coords: coords});
      favoritoModal.onDidDismiss(data => {
        console.log(data);
        if(data.favorito!==undefined){
          console.log("agregar");
          console.log(data);
          this.traerUsuario();
        }
        else{
          console.log("no agregar agregar");
        }
      });
      favoritoModal.present();
    }

    initMap(posicionu){
      let latLng = new google.maps.LatLng(posicionu.coords.latitude,posicionu.coords.longitude);
      let mapOptions ={
        center:latLng,
        zoom:11,
        disableDefaultUI: true,
        mapTypeId:google.maps.MapTypeId.ROADMAP
      };
      let maEl =document.getElementById("map");
      let map = new google.maps.Map(maEl,mapOptions);
      return map;

    }

    insertarBusqueda(map,that){
      var input = document.createElement("input");
      input.setAttribute("id", "pac-input");
      input.setAttribute("class", "controls");
      input.setAttribute("type", "text");
      input.setAttribute("placeholder", "¿Dónde buscas parqueadero?");

      var searchBox = new google.maps.places.SearchBox(input);

      map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);

      map.addListener('bounds_changed', function(bounds) {
        //  searchBox.setBounds(bounds);
      });

      searchBox.addListener('places_changed', function(){
        var places = searchBox.getPlaces();
        if (places.length == 0) {
          return;
        }
        var markers = [];

        markers.forEach(function(marker) {
          marker.setMap(null);
        });
        markers = [];
        var  bounds = new google.maps.LatLngBounds();
        places.forEach(function(place) {
          if (!place.geometry) {
            console.log("Returned place contains no geometry");
            return;
          }
          var icon = {
            url: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
            size: new google.maps.Size(100, 100),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(17, 34),
            scaledSize: new google.maps.Size(50, 50)
          };
          // Create a marker for each place.z
          markers.push();
          var marker = new google.maps.Marker({
            map: map,
            icon: icon,
            animation: google.maps.Animation.DROP,
            title: place.name,
            position: place.geometry.location
          });

          marker.addListener('click', function() {
            that.agregarFav(marker.getPosition().toJSON(),place.name);
          });

          markers.push(marker);

          if (place.geometry.viewport) {
            // Only geocodes have viewport.
            bounds.union(place.geometry.viewport);
          } else {
            bounds.extend(place.geometry.location);
          }
        });

        map.fitBounds(bounds);
      });

    }
    ///
    insertarParqueaderos(map, latLng ){
      let termino=true;
      var parqueaderos = [
        {id:1,nombre:'La bendicion',lat:4.600589044444201,long:-74.06797,precio:85,cupos:1,direccion:'Cr 28# 19-2'},
        {id:2,nombre:'La Alegria',lat:4.601228026690072,long:-74.067970,precio:45,cupos:6,direccion:'Cr 28# 19-2'},
        {id:3,nombre:'Ricos y famosos',lat:4.60405,long:-74.0657,precio:85,cupos:15,direccion:'Cr 28# 19-2'},
        {id:4,nombre:'Globalizacion',lat: 4.6036,long:-74.066,precio:79,cupos:0,direccion:'Cr 28# 19-2'},
        {id:5,nombre:'La loma',lat:4.604893,long:-74.065,precio:89,cupos:0,direccion:'Cr 28# 19-2'}
      ];
      var imageR = {
        url: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
        // This marker is 20 pixels wide by 32 pixels high.
        size: new google.maps.Size(50, 50),
        // The origin for this image is (0, 0).
        origin: new google.maps.Point(0, 0),
        // The anchor for this image is the base of the flagpole at (0, 32).
        anchor: new google.maps.Point(17, 32),
      };
      var imageY = {
        url: 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png',
        // This marker is 20 pixels wide by 32 pixels high.
        size: new google.maps.Size(50, 50),
        // The origin for this image is (0, 0).
        origin: new google.maps.Point(0, 0),
        // The anchor for this image is the base of the flagpole at (0, 32).
        anchor: new google.maps.Point(17, 32),
      };
      var imageG = {
        url: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
        // This marker is 20 pixels wide by 32 pixels high.
        size: new google.maps.Size(50, 50),
        // The origin for this image is (0, 0).
        origin: new google.maps.Point(0, 0),
        // The anchor for this image is the base of the flagpole at (0, 32).
        anchor: new google.maps.Point(17, 32),
      };

      let that = this;
      for (var i = 0; i < parqueaderos.length; i++) {
        (function(parqueadero,that){
          var lg = new google.maps.LatLng(Number(parqueadero.lat),Number(parqueadero.long));

          var marker = new google.maps.Marker({
            position: lg,
            map: map
          });
          if (parqueadero.cupos==0){
            marker.setIcon(imageR)
          }else if(parqueadero.cupos<10){
            marker.setIcon(imageY)
          }else{
            marker.setIcon(imageG)
          }
          //  infowindow.open(map, marker);
          marker.addListener('click', function() {
            that.presentProfileModal(parqueadero);
          });

        })(parqueaderos[i],that);

      }
      return termino;

    }
    ///
    insertarFavoritoMapa(map,latLng){

      var image = {
        url: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
        size: new google.maps.Size(100, 100),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(50, 50)
      };

      //
      var lg = latLng;
      var marker = new google.maps.Marker({
        position: lg,
        map: map,
        icon: image,
      });
      marker.setMap(map);

    }


  }
