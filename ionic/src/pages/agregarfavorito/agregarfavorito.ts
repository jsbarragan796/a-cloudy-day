import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { AlertController } from 'ionic-angular';
import { LoadingController } from 'ionic-angular';
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

  favorito={nombre:'',posicion:{coords:{}}};
  constructor(public loadingCtrl: LoadingController,public params: NavParams,public viewCtrl: ViewController,public storage: Storage,
  public alertCtrl: AlertController ) {
    this.favorito.posicion.coords=params.get('coords');
    this.favorito.nombre=params.get('nombre');
  }

  dismiss() {
    let data = { };
    this.viewCtrl.dismiss(data);
  }
  submitForm():void{
    let data = { favorito:this.favorito };
  //  this.presentLoading(1000,"Agregando lugar favorito")
    this.storage.get('usuario').then((val) => {
      console.log('Your age is', val);
      val.favoritos.push(this.favorito)
      this.storage.set('usuario',val)
      this.alertaErrorNoData("Lugar favorito agregado con exito");
    })
    console.log('Form submited!')

    this.viewCtrl.dismiss(data);
  }

  alertaErrorNoData(mensaje) {
  let alert = this.alertCtrl.create({
    title: 'Notificación',
    subTitle: mensaje,
    buttons: ['Aceptar']
  });
  alert.present();
}
presentLoading(duracion, mensaje) {
  let loader = this.loadingCtrl.create({
    content: mensaje,
    duration: duracion
  });
  loader.present();
}


}
