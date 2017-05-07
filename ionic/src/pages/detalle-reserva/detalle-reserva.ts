import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { Platform, ViewController } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  selector: 'page-detalle-reserva',
  templateUrl: 'detalle-reserva.html',
})
export class DetalleReserva {


  public parqueadero;
  disponible=true;
  usuario:any;
  authForm : FormGroup;


  constructor(public loadingCtrl: LoadingController,
    public platform: Platform, public params: NavParams,
    public alertCtrl: AlertController,public viewCtrl: ViewController,fb: FormBuilder,public storage: Storage) {
      console.log(params);
      this.parqueadero=params.get('parqueadero');
      if(this.parqueadero.cupos==0){
        this.disponible=false;
        this.alertaErrorNoData("Este parqueadero no cuenta con cupos")
      }
      this.usuario =this.storage.get('usuario').then((val) => {
        console.log('en vista reserva', val);
        this.usuario=val;
      }).catch((err)=>console.log('errror no se encunras'));
      this.authForm = fb.group({
        'vehiculo' : [null, Validators.compose([Validators.required])]
      })
    }

    ionViewDidLoad() {
      console.log('ionViewDidLoad DetalleReserva');
      console.log('ionViewDidLoad DetalleReserva');
    }

    createReserva(){
      let data = { idParqueadero: this.parqueadero.id };
      this.presentLoading(3000,"Esperando confirmación parqueadero");
      this.viewCtrl.dismiss(data);
    }

    dismiss() {
      let data = { idParqueadero: 'cancelar'};
      this.viewCtrl.dismiss(data);
    }
    presentLoading(duracion, mensaje) {
      let loader = this.loadingCtrl.create({
        content: mensaje,
        duration: duracion
      });
      loader.present();
    }
    alertaErrorNoData(mensaje) {
      let alert = this.alertCtrl.create({
        title: 'Notificación',
        subTitle: mensaje,
        buttons: ['Aceptar']
      });
      alert.present();
    }


  }
