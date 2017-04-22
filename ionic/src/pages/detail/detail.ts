import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

/**
* Generated class for the Detail page.
*
* See http://ionicframework.com/docs/components/#navigation for more info
* on Ionic pages and navigation.
*/

@Component({
  selector: 'page-detail',
  templateUrl: 'detail.html',
})
export class DetailPage {

  usuario={nombres:'',
           apellidos:'',
           usuario:''};
           
  authForm : FormGroup;


  constructor(public navCtrl: NavController, public navParams: NavParams,fb: FormBuilder) {
    this.usuario =navParams.get('usuario');

    this.authForm = fb.group({
		  'nombres' : [null, Validators.compose([Validators.required])],
		  'apellidos': [null, Validators.compose([Validators.required])],
		})
  }


  submitForm():void{
		console.log('Form submited!')
		console.log(this.authForm.value);
	}


}
