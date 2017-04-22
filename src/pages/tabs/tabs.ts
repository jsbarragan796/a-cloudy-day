import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
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

  constructor(params: NavParams ) {
    this.usuario= params.get('usuario');
    console.log("en tabs");
  }

  ionViewDidLoad(){
    console.log("en tabs!!");
    console.log(this.usuario);
  }

}
