import { Component } from '@angular/core';
import { NavController, NavParams} from 'ionic-angular';
import { TabsPage } from '../tabs/tabs';


@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {

  usuario:any;

  constructor(public navCtrl: NavController,public navParams: NavParams) {

    this.usuario=navParams.get('usuario');
  }

  irMapa(coords){

    var posicion={coords};
    var usuarioTab1={usuario:this.usuario,posicionFav: posicion};
    console.log(usuarioTab1);
    this.navCtrl.setRoot(TabsPage, {usuarioTab1:usuarioTab1 });
  }





}
