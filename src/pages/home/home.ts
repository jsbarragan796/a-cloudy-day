import { Component,ViewChild } from '@angular/core';
import { NavController } from 'ionic-angular';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {


  @ViewChild('map') mapElement;
  map:any;
  searchBox:any;

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

    this.map = new google.maps.Map(this.mapElement.nativeElement,mapOptions);
    var input = document.createElement("input");
    input.setAttribute("id", "pac-input");
    input.setAttribute("class", "controls");
    input.setAttribute("type", "text");
    input.setAttribute("placeholder", "¿Dónde buscas parqueadero?");

    this.searchBox = new google.maps.places.SearchBox(input);

    this.map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
    this.map.addListener('bounds_changed', function() {
      
    });
  }


}
