import { Component, ViewChild } from '@angular/core';
import { Nav, Platform, ModalController } from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';

import { Dashboard } from '../pages/dashboard/dashboard';
import { Page2 } from '../pages/page2/page2';
import { Login } from '../pages/login/login';
import { LoginService } from '../providers/login-service';

@Component({
  templateUrl: 'app.html',
  providers: [LoginService]
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = Dashboard;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform, public modalCtrl: ModalController, public loginService: LoginService) {
    this.initializeApp();

    // used for an example of ngFor and navigation
    this.pages = [
      { title: 'Active Bids', component: Dashboard },
      { title: 'Page Two', component: Page2 }
    ];

  }

   presentLogineModal() {
    let loginModal = this.modalCtrl.create(Login);

    loginModal.onDidDismiss(data => {
      this.nav.setRoot(Dashboard);
    });
    loginModal.present();
  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      StatusBar.styleDefault();
      Splashscreen.hide();
      this.loginService.isTokenSaved().then(saved => {
        if(!saved) {
          this.presentLogineModal();
        } else {
          this.nav.setRoot(Dashboard);
        }
      });
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }

  logout() {
    this.loginService.cleanTheToken();
    this.presentLogineModal();
  }
}
