import { Component,ViewChild } from '@angular/core';
import { NavController } from 'ionic-angular';


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {


  @ViewChild('map') mapElement;
  items:any[];
  map:any;


  constructor(public navCtrl: NavController) {

    this.items=[]
    for (let i=0;i<10;i++) {
      this.items.push({text: 'Item '+(i+1),
      id:i});
    }
  }


ionViewDidLoad(){
  this.initMap();
}

initMap(){
  var currentdate =new Date();
  var hora=currentdate.getHours();
  let latLng = new google.maps.LatLng(-34.92,138.60);
  let mapOptions ={
    center:latLng,
    zoom:15,
    disableDefaultUI: true,

    mapTypeId:google.maps.MapTypeId.ROADMAP

  };
  this.map = new google.maps.Map(this.mapElement.nativeElement,mapOptions);
}

}
