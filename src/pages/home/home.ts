import { Component,ViewChild } from '@angular/core';
import { Geolocation } from '@ionic-native/geolocation';
import { Platform } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  @ViewChild('map') mapElement;
  map:any;
  lati:any;
  longi:any;
  posicion:any=undefined;

  constructor(public loadingCtrl: LoadingController,private platform: Platform, private geolocation: Geolocation) {

  }

  presentLoading() {
    let loader = this.loadingCtrl.create({
      content: "Cargando... ",
      duration: 3000
    });
    loader.present();
  }

  locateUser(){
    this.presentLoading();
    this.geolocation.getCurrentPosition().then((pos) => {
      this.posicion=pos;
      this.initMap();
    });
  }
  locateUserBoton(){
    this.geolocation.getCurrentPosition().then((pos) => {
      this.posicion=pos;
      this.initMap();
    });
  }
  ionViewDidLoad(){
    this.locateUser();
  }

  initMap(){

    let latLng = new google.maps.LatLng(this.posicion.coords.latitude,this.posicion.coords.longitude);
    let mapOptions ={
      center:latLng,
      zoom:15,
      disableDefaultUI: true,
      mapTypeId:google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(this.mapElement.nativeElement,mapOptions);
    var input = document.createElement("input");
    input.setAttribute("id", "pac-input");
    input.setAttribute("class", "controls");
    input.setAttribute("type", "text");
    input.setAttribute("placeholder", "¿Dónde buscas parqueadero?");

    var searchBox = new google.maps.places.SearchBox(input);

    map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
    map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });

    searchBox.addListener('places_changed', function() {
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
        markers.push(new google.maps.Marker({
          map: map,
          icon: icon,
          animation: google.maps.Animation.DROP,
          title: place.name,
          position: place.geometry.location
        }));

        if (place.geometry.viewport) {
          // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });

      map.fitBounds(bounds);
    });

    this.map =map;


    var parqueaderos = [
      ['La bendicion',4.600589044444201, -74.06688451766968, 85 , 300, 1],
      ['La Alegria',4.601228026690072, -74.067970, 45 , 300, 90],
      ['Ricos y famosos' ,4.60405,-74.0657, 75 , 300, 33],
      ['Globalizacion', 4.6036, -74.066,55 , 300, 11],
      ['La loma',4.604893, -74.065, 95 , 300, 10]
    ];

    var image = {
      url: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
      // This marker is 20 pixels wide by 32 pixels high.
      size: new google.maps.Size(50, 50),
      // The origin for this image is (0, 0).
      origin: new google.maps.Point(0, 0),
      // The anchor for this image is the base of the flagpole at (0, 32).
      anchor: new google.maps.Point(17, 32),
    };



    for (var i = 0; i < parqueaderos.length; i++) {
      (function(parqueadero){
        var lg = new google.maps.LatLng(Number(parqueadero[1]),Number(parqueadero[2]));
        var contenidoString = '<div>'+
        '<p>'+parqueadero[0]+'</p>'+
        '<p>'+parqueadero[3]+' pesos/min</p>'+
        '<p>'+parqueadero[4]+' metros a destino</p>'+
        '<p>'+parqueadero[5]+' cupos disponibles</p>'+
        '<button ion-button>'+
        'Reservar'+
        '</button>'+
        '</div';

        var infowindow = new google.maps.InfoWindow({
          content: contenidoString,
          maxWidth: 70
        });
        var marker = new google.maps.Marker({
          position: lg,
          map: map,
          icon: image,
          title: String(parqueadero[1])
        });
        marker.addListener('click', function() {
          infowindow.open(map, marker);
        });
      })(parqueaderos[i]);
    }

  }
}
