import { Component } from '@angular/core';
import { NavController, NavParams} from 'ionic-angular';
import { TabsPage } from '../tabs/tabs';


@Component({
  selector: 'page-about',
  templateUrl: 'inforeserva.html'
})
export class InfoReserva {

  usuario:any;
  public parqueadero;

  constructor(public navCtrl: NavController,public navParams: NavParams) {
    console.log("info reserva");
    console.log(navParams);
    this.usuario=navParams.get('usuario');
    this.parqueadero=navParams.get('parqueadero');
  }

  irMapa(){

    var usuarioTab1={usuario:this.usuario};
    console.log(usuarioTab1);
    this.navCtrl.setRoot(TabsPage, {usuarioTab1:usuarioTab1 });
  }





}
