import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'page-det-vehiculo',
  templateUrl: 'det-vehiculo.html',
})
export class DetVehiculo {

  authForm : FormGroup;
  vehiculo={tipo:'',
  placa:''};

  constructor(public navCtrl: NavController, public navParams: NavParams, fb: FormBuilder) {

    if(navParams.get('vehiculo')!==undefined){
      this.vehiculo =navParams.get('vehiculo');
    }
    this.authForm = fb.group({
      'tipo' : this.vehiculo.tipo,
      'placa': [null, Validators.compose([Validators.required])],
    })
  }

  submitForm():void{
    console.log('Form submited!')
    this.vehiculo=this.authForm.value;
    console.log(this.authForm.value);
    console.log(this.vehiculo);
    this.navCtrl.pop();
  }



}
