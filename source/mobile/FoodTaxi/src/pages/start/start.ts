import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';
import { Page1 } from '../page1/page1';
import { Login } from '../login/login';

@Component({
  selector: 'page-start',
  templateUrl: 'start.html'
})
export class Start {
  
  constructor(public navCtrl: NavController) {
  	this.navCtrl.push(Login)
  }
}
