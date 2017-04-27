import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import {DetVehiculo} from '../det-vehiculo/det-vehiculo';
import { Storage } from '@ionic/storage';



@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {


  usuario:any;

  constructor(public navCtrl: NavController,pgarams: NavParams,public storage: Storage) {

  }

  ionViewDidLoad(){

      this.usuario =this.storage.get('usuario').then((val) => {
         console.log('en vista contacto', val);
         this.usuario=val;
       })



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
