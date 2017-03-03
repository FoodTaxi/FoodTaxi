import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-start',
  templateUrl: 'start.html'
})
export class Start {
  public authenticated = false;
  constructor(public navCtrl: NavController) {
  }

  login() {
  	this.authenticated = true;
  }
}
