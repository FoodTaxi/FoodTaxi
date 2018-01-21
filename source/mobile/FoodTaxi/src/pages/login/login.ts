import { Component } from '@angular/core';

import { ViewController, LoadingController, NavController } from 'ionic-angular';
import { LoginService } from '../../providers/login-service';
import { Dashboard } from '../dashboard/dashboard';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
  providers: [LoginService]
})
export class Login {
	username:string;
    password:string;

  constructor(public viewCtrl: ViewController, public navCtrl: NavController, public loadingCtrl: LoadingController, public loginService: LoginService) {
  }

  login() {

	let loading = this.loadingCtrl.create({
		content: 'Please wait...'
	});

	loading.present();
	this.loginService.login(this.username, this.password)
		.then(data => {
			loading.dismiss();
			this.navCtrl.setRoot(Dashboard);
			// this.navCtrl.popToRoot();
		}, err => {
			console.log(err);
			alert(JSON.parse(err._body).message);
			loading.dismiss();
		});
  }

  onLogin() {

  }
}
