import { Component } from '@angular/core';

import { ModalController, NavController } from 'ionic-angular';
import {BidService} from '../../providers/bid-service';
import {OrderService} from '../../providers/order-service';
import {Order} from '../order/order';
import {Start} from '../start/start';


@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
  providers: [BidService,OrderService]
})
export class Dashboard {
  public orders = [];
  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public orderService: OrderService) {
    this.presentLogineModal();
  }

  presentLogineModal() {
    let lofinModal = this.modalCtrl.create(Start);

    lofinModal.onDidDismiss(data => {
      this.loadOrders();
    });
    lofinModal.present();
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
