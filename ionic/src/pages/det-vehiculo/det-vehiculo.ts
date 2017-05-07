import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Storage } from '@ionic/storage';
import { LoadingController } from 'ionic-angular';
import { AlertController } from 'ionic-angular';

@Component({
  selector: 'page-det-vehiculo',
  templateUrl: 'det-vehiculo.html',
})
export class DetVehiculo {

  authForm : FormGroup;
  vehiculo={tipo:'',
  placa:''};

  constructor(public loadingCtrl: LoadingController,public alertCtrl: AlertController,
    public navCtrl: NavController, public navParams: NavParams, fb: FormBuilder,public storage: Storage) {

    if(navParams.get('vehiculo')!==undefined){
      this.vehiculo =navParams.get('vehiculo');
    }
    this.authForm = fb.group({
      'tipo' : [null, Validators.compose([Validators.required])],
      'placa': [null, Validators.compose([Validators.required])],
    })
  }

  submitForm():void{
    console.log('Form submited!')
  //  this.presentLoading(1000,"Agregando vehículo")
    this.vehiculo=this.authForm.value;
    console.log(this.authForm.value);
    console.log(this.vehiculo);
    this.storage.get('usuario').then((val) => {
         console.log('agregar vehiculo', val);
         val.vehiculos.push(this.authForm.value)
         this.storage.set('usuario',val);
         this.alertaErrorNoData("Nuevo vehículo agregado")
       })
    this.navCtrl.pop();
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
