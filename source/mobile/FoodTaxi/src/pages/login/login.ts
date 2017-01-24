import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';
import { Page1 } from '../page1/page1';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class Login {
  
  constructor(public navCtrl: NavController) {

  }

  login() {
  	this.navCtrl.popToRoot();

  }
}
