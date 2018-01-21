import { Component } from '@angular/core';

import { ModalController, NavController, LoadingController } from 'ionic-angular';
import {BidService} from '../../providers/bid-service';
import {DeliveryService} from '../../providers/delivery-service';
import {Delivery} from '../delivery/delivery';
import {Login} from '../login/login';
import {MapPage} from '../map/map';
import { DriverService } from '../../providers/driver-service';
import { LoginService } from '../../providers/login-service';



@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
  providers: [BidService, DeliveryService, DriverService, LoginService]
})
export class Dashboard {
  public deliveries: any;
  public profile: any;
  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public deliveryService: DeliveryService,
    public driverService: DriverService, public loginService: LoginService, public loadingCtrl: LoadingController,
    public bidService: BidService ) {
    
  }
  
  ionViewWillEnter() {
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

  openShopMap(delivery) {
    this.navCtrl.push(MapPage, {
      latitude: delivery.startLatitude,
      longitude: delivery.startLongtitude,
      nameOfPoint: 'Shop'

    });
  }

  openClientMap(delivery) {
    this.navCtrl.push(MapPage, {
      latitude: delivery.endLatitude,
      longitude: delivery.endLongtitude,
      nameOfPoint: 'End point'

    });
  }
  
  newBidPrice(delivery) {
     return (delivery.bestBidAmount - 0.10).toFixed(2);
  }

  currentBidPrice(delivery) {
     return delivery.bestBidAmount.toFixed(2);
  }

  makeABid(delivery) {
    let loading = this.loadingCtrl.create({
      content: 'Please wait...'
    });
    loading.present();
    this.bidService.createBid(delivery.id, this.newBidPrice(delivery)).then(data => {
      loading.dismiss();
    });;
  } 


  logout() {
    this.loginService.cleanTheToken();
    this.navCtrl.setRoot(Login);
  }
}
