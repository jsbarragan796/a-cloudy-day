import { Component } from '@angular/core';
import {NavParams} from 'ionic-angular';
import { ViewController } from 'ionic-angular';

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {

  favoritos:any;

  constructor(public viewCtrl: ViewController,public navParams: NavParams) {
    this.favoritos=navParams.get('favoritos');
  }


  dismiss() {
    let data = { };
    this.viewCtrl.dismiss(data);
  }

  irMapa(coords){
    var data=coords;
    this.viewCtrl.dismiss(data);
  }





}
