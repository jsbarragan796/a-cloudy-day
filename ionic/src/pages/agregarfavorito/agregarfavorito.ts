import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { Platform, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';

/**
* Generated class for the DetalleReserva page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'agregarfavorito',
  templateUrl: 'agregarfavorito.html',
})
export class AgregarFavorito {

  favorito={nombre:'',coords:{}};
  constructor(public platform: Platform, public params: NavParams,public viewCtrl: ViewController,public storage: Storage ) {
    console.log(params);
    this.favorito.coords=params.get('coords');
    this.favorito.nombre=params.get('nombre');

  }


  dismiss() {
    let data = { };
    this.viewCtrl.dismiss(data);
  }
  submitForm():void{
    let data = { favorito:this.favorito };
    this.storage.get('usuario').then((val) => {
      console.log('Your age is', val);
      val.favoritos.push(this.favorito)
      this.storage.set('usuario',val)
    })
    console.log('Form submited!')
    this.viewCtrl.dismiss(data);
  }


}
