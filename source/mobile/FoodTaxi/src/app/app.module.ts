import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { Login } from '../pages/login/login';
import { Dashboard } from '../pages/dashboard/dashboard';
import { Page2 } from '../pages/page2/page2';
import { Delivery } from '../pages/delivery/delivery';
import { AuthHttp, AuthConfig } from 'angular2-jwt';
import { Http } from '@angular/http';
import { IonicStorageModule, Storage } from '@ionic/storage';

let storage = new Storage();

export function getAuthHttp(http) {
  return new AuthHttp(new AuthConfig({
    headerName: 'authorization',
    headerPrefix: 'Bearer ',
    noJwtError: true,
    globalHeaders: [{'Accept': 'application/json'}],
    tokenGetter: (() => storage.get('token')),
  }), http);
}

@NgModule({
  declarations: [
    MyApp,
    Login,
    Dashboard,
    Page2,
    Delivery
  ],
  imports: [
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    Login,
    Dashboard,
    Page2,
    Delivery
  ],

  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler},
   {provide: AuthHttp, useFactory: getAuthHttp, deps: [Http]}
  ]
})
export class AppModule {}
