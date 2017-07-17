import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';
import {LoginService} from '../../providers/login-service';
import {Dashboard} from '../dashboard/dashboard';

@Component({
  selector: 'page-start',
  templateUrl: 'start.html',
  providers: [LoginService, Dashboard]
})
export class Start {
  public authenticated = false;
  constructor(public navCtrl: NavController,public loginService: LoginService) {
  }

  login(username, password) {
  	console.log('yes');
  	this.authenticated = true;
  	this.loginService.login(username, password)
  	.then(data => {
  		this.navCtrl.push(Dashboard);
  	});
  }
}
