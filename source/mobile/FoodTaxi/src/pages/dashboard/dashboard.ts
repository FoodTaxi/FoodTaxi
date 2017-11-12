import { Component } from '@angular/core';

import { ModalController, NavController } from 'ionic-angular';
import {BidService} from '../../providers/bid-service';
import {DeliveryService} from '../../providers/delivery-service';
import {Delivery} from '../delivery/delivery';
import { DriverService } from '../../providers/driver-service';


@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
  providers: [BidService, DeliveryService, DriverService]
})
export class Dashboard {
  public deliveries: any;
  public profile: any;
  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public deliveryService: DeliveryService,
    public driverService: DriverService) {
    this.loadDeliveries();
    this.loadProfile();
  }

  loadProfile() {
    this.driverService.getProfile()
    .then(data => {
      this.profile = data;
    })
  }

  loadDeliveries(){
    this.deliveryService.getOpenDeliveries()
    .then(data => {
      this.deliveries = data;
    });
  }

  openDelivery(delivery) {
    this.navCtrl.push(Delivery, {
      delivery: delivery
    });
  }
}
