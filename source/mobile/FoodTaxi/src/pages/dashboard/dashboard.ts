import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';
import {BidService} from '../../providers/bid-service';
import {OrderService} from '../../providers/order-service';
import {Order} from '../order/order';

@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
  providers: [BidService,OrderService]
})
export class Dashboard {
  public orders = [];
  constructor(public navCtrl: NavController, public orderService: OrderService) {
    this.loadOrders();
  }

  loadOrders(){
    this.orderService.load()
    .then(data => {
      this.orders = data;
    });
  }

  openOrder(order) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(Order, {
      order: order
    });
  }
}
