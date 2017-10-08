import { Component } from '@angular/core';

import { ModalController, NavController } from 'ionic-angular';
import {BidService} from '../../providers/bid-service';
import {DeliveryService} from '../../providers/delivery-service';
import {Delivery} from '../delivery/delivery';
import {Login} from '../login/login';


@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
  providers: [BidService,DeliveryService]
})
export class Dashboard {
  public deliveries = [];
  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public deliveryService: DeliveryService) {
    // this.presentLogineModal();
    this.loadDeliveries();
  }

  presentLogineModal() {
    let loginModal = this.modalCtrl.create(Login);

    loginModal.onDidDismiss(data => {
      this.loadDeliveries();
    });
    loginModal.present();
  }

  loadDeliveries(){
    this.deliveryService.load()
    .then(data => {
      this.deliveries = data;
    });
  }

  openDelivery(delivery) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(Delivery, {
      delivery: delivery
    });
  }
}
