import { Component,ViewChild } from '@angular/core';
import { NavController } from 'ionic-angular';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {


  @ViewChild('map') mapElement;
  map:any;
  constructor(public navCtrl: NavController) {

  }

  ionViewDidLoad(){
    this.initMap();
  }

  initMap(){

    let latLng = new google.maps.LatLng(-34.92,138.60);
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
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(25, 25)
        };

        // Create a marker for each place.
        markers.push(new google.maps.Marker({
          map: map,
          icon: icon,
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
  }
}
