import { Component } from '@angular/core';

import { ViewController, LoadingController } from 'ionic-angular';
import { LoginService } from '../../providers/login-service';

@Component({
  selector: 'page-start',
  templateUrl: 'start.html',
  providers: [LoginService]
})
export class Start {
  public authenticated = false;
  constructor(public viewCtrl: ViewController, public loadingCtrl: LoadingController, public loginService: LoginService) {
  }

  login(username, password) {
  	this.authenticated = true;
	let loading = this.loadingCtrl.create({
		content: 'Please wait...'
	});

	loading.present();
	this.loginService.login(username, password)
		.then(data => {
		loading.dismiss();
		this.viewCtrl.dismiss(data);
	});
  }
}
