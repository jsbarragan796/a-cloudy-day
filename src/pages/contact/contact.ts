import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import {DetailPage} from '../detail/detail';
import {DetVehiculo} from '../det-vehiculo/det-vehiculo';


@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {


  usuario={usuario:'jusebast',nombres:'Juan Sebastian',
  apellidos:'Barragan Jeronimo',
  vehiculos:[{tipo:'Camioneta',
  placa:'ZYW 000'},{tipo:'Camioneta',
  placa:'ZAA 000'}]
 };

constructor(public navCtrl: NavController) {


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
