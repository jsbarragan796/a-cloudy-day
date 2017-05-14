import { Component } from '@angular/core';
import {NavController} from 'ionic-angular';
import { ContactPage } from '../contact/contact';
import { HomePage } from '../home/home';
import { InicioPerfil } from '../inicio-perfil/inicio-perfil';
import { Storage } from '@ionic/storage';
import { ModalController} from 'ionic-angular';
import {DetailPage} from '../detail/detail'


@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = ContactPage;
  tab3Root = DetailPage;
  usuario:any;
  constructor(public navCtrl: NavController,public storage: Storage,public modalCtrl: ModalController ) {

    this.usuario={usuarioName:'jusebast',nombres:'Juan Sebastian',
      apellidos:'Barragan Jeronimo',
      vehiculos:[{tipo:'Camioneta',
      placa:'ZYW 000'},{tipo:'Camioneta',
      placa:'ZAA 000'}],
      favoritos:[{nombre:'La universidad',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}},
      {nombre:'Donde pacho',posicion:{coords:{latitude:4.6036,longitude:-74.0668845}}},
      {nombre:'Usaquen',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}},
      {nombre:'Tía Olga',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}}]
       };
    this.storage.set('usuario',this.usuario).catch((err)=>{console.log('no deberia pasar por aca')});
  }

  ionViewDidLoad(){

  console.log("se guardo el usuario")
  console.log(this.usuario)
  }

}
