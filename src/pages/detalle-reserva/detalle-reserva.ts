import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { Platform, ViewController } from 'ionic-angular';

/**
* Generated class for the DetalleReserva page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'page-detalle-reserva',
  templateUrl: 'detalle-reserva.html',
})
export class DetalleReserva {


  public parqueadero;



  constructor(public platform: Platform, public params: NavParams,public viewCtrl: ViewController) {
      console.log(params);
    this.parqueadero=params.get('parqueadero');
    }

    ionViewDidLoad() {
      console.log('ionViewDidLoad DetalleReserva');
        console.log('ionViewDidLoad DetalleReserva');
    }

  createReserva(){
    let data = { idParqueadero: this.parqueadero.id };
    this.viewCtrl.dismiss(data);
  }

   dismiss() {
   let data = { idParqueadero: 'cancelar' };
   this.viewCtrl.dismiss(data);
 }


  }
