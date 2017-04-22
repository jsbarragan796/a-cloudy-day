import { Component } from '@angular/core';
import { NavController, NavParams} from 'ionic-angular';
import { HomePage } from '../home/home';


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
    //console.log(coords);
    var posicion={coords};
    this.navCtrl.setRoot(HomePage, {usuario:this.usuario,
                                    posicion: posicion});
  //  this.navCtrl.pop();
//    (HomePage, {posicion: posicion});
  }





}
