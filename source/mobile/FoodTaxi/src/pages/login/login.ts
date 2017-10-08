import { Component } from '@angular/core';

import { ViewController, LoadingController } from 'ionic-angular';
import { LoginService } from '../../providers/login-service';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
  providers: [LoginService]
})
export class Login {
  public authenticated = false;
  constructor(public viewCtrl: ViewController, public loadingCtrl: LoadingController, public loginService: LoginService) {
  }

  login(username, password) {

	let loading = this.loadingCtrl.create({
		content: 'Please wait...'
	});

	loading.present();
	this.loginService.login(username, password)
		.then(data => {
			this.authenticated = true;
			loading.dismiss();
			this.viewCtrl.dismiss(data);
		}, err => {
			this.authenticated = false;
			console.log(err);
			alert(JSON.parse(err._body).message);
			loading.dismiss();
		});
  }
}
