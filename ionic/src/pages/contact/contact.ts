import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {DetVehiculo} from '../det-vehiculo/det-vehiculo';



@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {


  usuario:any;

  constructor(public navCtrl: NavController,params: NavParams) {

    if(params.data!==undefined){
      this.usuario =params.data;
    }


  }

  viewVehiculo(vehiculo){
    this.navCtrl.push(DetVehiculo, {
      vehiculo:vehiculo
    });
  }

  createVehiculo(){
    this.navCtrl.push(DetVehiculo, {vehiculo:undefined});

  }

}
