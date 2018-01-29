import { Component } from '@angular/core';

import { ModalController, NavController, LoadingController } from 'ionic-angular';
import {BidService} from '../../providers/bid-service';
import {DeliveryService} from '../../providers/delivery-service';
import {Delivery} from '../delivery/delivery';
import {Login} from '../login/login';
import {MapPage} from '../map/map';
import { DriverService } from '../../providers/driver-service';
import { LoginService } from '../../providers/login-service';
import {Observable} from 'rxjs/Rx';

@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
  providers: [BidService, DeliveryService, DriverService, LoginService]
})
export class Dashboard {
  public deliveries: any;
  public profile: any;
  private secondsTimer: any;

  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public deliveryService: DeliveryService,
    public driverService: DriverService, public loginService: LoginService, public loadingCtrl: LoadingController,
    public bidService: BidService ) {
    this.secondsTimer = Observable.timer(1000,1000);
      this.secondsTimer.subscribe(t=> {
        this.updateTime(this.deliveries);
      });
    
  }
  
  ionViewWillEnter() {
    this.loadProfile();
  }

  loadProfile() {
    this.driverService.getProfile()
    .then(data => {
      console.log(data);
      this.profile = data;
      this.loadDeliveries();
    });
  }

  loadDeliveries(){
    this.deliveryService.getOpenDeliveries()
    .then(data => {
      this.deliveries = data;
      this.updateTime(this.deliveries);
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
    if (delivery.bestBidAmount ) {
      return (delivery.bestBidAmount - 0.10).toFixed(2);
    } else {
      return '5.00';
    }
  }

  currentBidPrice(delivery) {
    if (delivery.bestBidAmount ) {
      return delivery.bestBidAmount.toFixed(2);
    } else {
      return 5.00;
    }
  }
  
  formatTime(timestamp) {
    var currentTime =  Math.floor(Date.now());
    currentTime = timestamp - currentTime
    currentTime = Math.floor(currentTime/1000)
    return Math.floor(currentTime/60) + ':' + Math.abs(currentTime%60);
  }

  updateTime(deliveries) {
     for (let delivery of deliveries) {
      delivery.timeleft = this.formatTime(delivery.expectedBidEnd);
    }
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


  getDistanceFromLatLonInKm(delivery) {
    var lat1 = delivery.startLatitude;
    var lon1 = delivery.startLongtitude;
    var lat2 = delivery.endLatitude;
    var lon2 = delivery.endLongtitude;
    var R = 6371; // Radius of the earth in km
    var dLat = this.deg2rad(lat2-lat1);  // deg2rad below
    var dLon = this.deg2rad(lon2-lon1); 
    var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * 
            Math.sin(dLon/2) * Math.sin(dLon/2); 
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
    var d = R * c; // Distance in km
    return d.toFixed(2);
  }

  deg2rad(deg) {
    return deg * (Math.PI/180)
  }

 openDelivery(delivery) {
    let deliveryModal = this.modalCtrl.create(Delivery, { delivery: delivery });
    deliveryModal.onDidDismiss(data => {
       console.log(data);
     });
    deliveryModal.present();
  }

  isMine(currentOwner) {
    return currentOwner == this.profile.id;
  }

  logout() {
    this.loginService.cleanTheToken();
    this.navCtrl.setRoot(Login);
  }
}
