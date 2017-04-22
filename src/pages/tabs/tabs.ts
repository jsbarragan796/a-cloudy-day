import { Component } from '@angular/core';
import {NavController, NavParams } from 'ionic-angular';
import { ContactPage } from '../contact/contact';
import { HomePage } from '../home/home';


@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = ContactPage;
  tab3Root = ContactPage;

  usuario:any;
  usuarioTab1:any;

  constructor(public params: NavParams,  public navCtrl: NavController ) {
    if(params.get('usuarioTab1')!==undefined){
      this.usuarioTab1= params.get('usuarioTab1');
      this.usuario= this.usuarioTab1.usuario;
        console.log("en tabs con tab1 def");
    }
    else {
      this.usuario= params.get('usuario');
      this.usuarioTab1={usuario:this.usuario};
      console.log("en tabs no def ");
    }
  
  }

  ionViewDidLoad(){
    console.log("en tabs!!");
    if(this.usuarioTab1=undefined)
    console.log(this.usuarioTab1);
  }

}
