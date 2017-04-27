import { Component } from '@angular/core';
import {NavController, NavParams } from 'ionic-angular';
import { ContactPage } from '../contact/contact';
import { HomePage } from '../home/home';
import { InicioPerfil } from '../inicio-perfil/inicio-perfil';
import { Storage } from '@ionic/storage';
import { ModalController, Platform } from 'ionic-angular';


@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = ContactPage;
  tab3Root = ContactPage;
  usuario:any;
//  usuarioTab1:any;

  constructor(public params: NavParams,  public navCtrl: NavController,public storage: Storage,public modalCtrl: ModalController ) {

  }

  ionViewDidLoad(){
  let  usuario={usuarioName:'jusebast',nombres:'Juan Sebastian',
    apellidos:'Barragan Jeronimo',
    vehiculos:[{tipo:'Camioneta',
    placa:'ZYW 000'},{tipo:'Camioneta',
    placa:'ZAA 000'}],
    favoritos:[{nombre:'La universidad',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}},
    {nombre:'Donde pacho',posicion:{coords:{latitude:4.6036,longitude:-74.0668845}}},
    {nombre:'Usaquen',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}},
    {nombre:'Tía Olga',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}}]
     };
    console.log("en tabs!!");
    if (this.usuario===undefined){
      let loginModal = this.modalCtrl.create(InicioPerfil);
      loginModal.onDidDismiss(data => {
        console.log(data);
        if(data.usuarioName!==undefined){
          this.storage.set('usuario',usuario)
        }
      });
       loginModal.present();
    }
  console.log("se guardo el usuario")
  console.log(this.usuario)
  }

}
