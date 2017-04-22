import { Component } from '@angular/core';
import {  NavController } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import{ TabsPage} from "../tabs/tabs";
import { Geolocation } from '@ionic-native/geolocation';

/**
* Generated class for the InicioPerfil page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/
@Component({
  selector: 'inicio-perfil',
  templateUrl: 'inicio-perfil.html'
})
export class InicioPerfil {

  usuario={usuarioName:'jusebast',nombres:'Juan Sebastian',
  apellidos:'Barragan Jeronimo',
  vehiculos:[{tipo:'Camioneta',
  placa:'ZYW 000'},{tipo:'Camioneta',
  placa:'ZAA 000'}],
  favoritos:[{nombre:'La universidad',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}},
  {nombre:'Donde pacho',posicion:{coords:{latitude:4.6036,longitude:-74.0668845}}},
  {nombre:'Usaquen',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}},
  {nombre:'Tía Olga',posicion:{coords:{latitude:4.60836,longitude:-74.0668845}}}]
};


authForm : FormGroup;


constructor(public navCtrl: NavController,fb: FormBuilder,private geolocation: Geolocation) {
  this.authForm = fb.group({
    'usuarioName' : [null, Validators.compose([Validators.required])]
  });
  var ee= geolocation.getCurrentPosition().then((pos) => {
    console.log(pos);
  }).catch((error)=>console.log("error en la geolocation"));
}




submitForm():void{
  //this.navCtrl.setRoot(HomePage, {posicion: posicion});
  this.navCtrl.setRoot(TabsPage, {usuario: this.usuario});

}

}
