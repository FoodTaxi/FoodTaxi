import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { Dashboard } from '../pages/dashboard/dashboard';
import { Page2 } from '../pages/page2/page2';
import { Order } from '../pages/order/order';

@NgModule({
  declarations: [
    MyApp,
    Dashboard,
    Page2,
    Order
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    Dashboard,
    Page2,
    Order
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}]
})
export class AppModule {}
