import { Component } from '@angular/core';

import { ModalController, NavController, LoadingController, AlertController } from 'ionic-angular';
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
  private refreshDelivaries2sec: any;
  private refreshDelivaries10sec: any;
  private refreshSubscriber: any;
  private isInBidding: boolean;
  private title: String;
  private currentDelivery: any;

  constructor(public modalCtrl: ModalController, public navCtrl: NavController, public deliveryService: DeliveryService,
    public driverService: DriverService, public loginService: LoginService, public loadingCtrl: LoadingController,
    public bidService: BidService, private alertController: AlertController ) {
    this.title = "Търсене на поръчки";
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
      this.generateTimers();
      this.loadDeliveries();
    });
  }
  generateTimers() {
    this.refreshDelivaries10sec = Observable.timer(10000,10000);
    this.refreshDelivaries2sec = Observable.timer(1000,2000);
    this.updateTimerInterval(false);
  }

  loadDeliveries(){
    this.deliveryService.getOpenDeliveries()
    .then(data => {
      console.log('delivery');
      this.deliveries = data;
      this.checkForBidding();
      this.updateTime(this.deliveries);
    });
  }

  checkForBidding() {
    var bidding = false; 
    for (let delivery of this.deliveries) {
        if (delivery.status == 'BIDDING') {
           bidding = true; 
           break;
        }
    }
    if (this.isInBidding != bidding) {
      this.updateTimerInterval(bidding);
    }
  }

  updateTimerInterval(bidding) {
    this.isInBidding = bidding;
    if (this.refreshSubscriber) {
      this.refreshSubscriber.unsubscribe();
    }
    if (this.isInBidding) {
      this.refreshSubscriber = this.refreshDelivaries2sec.subscribe(t=> {
        this.loadDeliveries();
      });
    } else {
      this.refreshSubscriber = this.refreshDelivaries10sec.subscribe(t=> {
        this.loadDeliveries();
      });
    }
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
      return '5.00';
    }
  }
  
  formatTime(timestamp) {
    var currentTime =  Math.floor(Date.now());
    currentTime = timestamp - currentTime
    if (currentTime < 0) {
      return "";
    }
    currentTime = Math.floor(currentTime/1000)
    
    return this.twoDigits(Math.floor(currentTime/60)) + ':' + this.twoDigits(Math.abs(currentTime%60));
  }

  twoDigits(number) {
    if (number > -10  && number <10) {
      return "0" + number;
    } 
    return "" + number;
  }

  updateTime(deliveries) {
    if (deliveries === undefined  || deliveries.length == 0) {
      this.title = "Търсене на поръчки";
      return;
    }
    for (let delivery of deliveries) {
      if (delivery.status == 'PICKING_UP') {
        this.title = "На път към обекта";
        delivery.timeleft = this.formatTime(delivery.pickupDueDate);
      } else if (delivery.status == 'DELIVERY') {
        this.title = "На път към клиента";
        delivery.timeleft = this.formatTime(delivery.dueDate);
      } else {
        delivery.timeleft = this.formatTime(delivery.expectedBidEnd);
      }
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


  calculateDeliveryTime(delivery) {
    var currentTime =  Math.floor(Date.now());
    currentTime = delivery.pickupDueDate - currentTime
    currentTime = Math.floor(currentTime/1000)
    if (currentTime < 0) {
      return "0";
    }
    return this.twoDigits(Math.floor(currentTime/60));
  }

  presentPinPropmpt(delivery) {
    let alert = this.alertController.create({
      title: 'ПИН',
      message: 'Вашият ПИН за поръчка #'+ delivery.id + ' е: ' + delivery.pin,      
      buttons: [{
        text: 'OK',
        role: 'cancel'
      }]
    });
    alert.present();
  }

   presentNextToClient(delivery) {
    this.currentDelivery = delivery;
    let alert = this.alertController.create({
      title: 'При клиента',
      message: 'Име '+ delivery.custName + ' телфон: ' + delivery.custPhone,      
      buttons: [{
        text: 'Откажи',
        role: 'cancel'
      },{
        text: 'Предадох',
        handler: () => {
          this.delivered(this.currentDelivery);
        }
      }]
    });
    alert.present();
  }


  delivered(delivery) {
     let loading = this.loadingCtrl.create({
        content: 'Моля, изчакайте...'
      });
      loading.present();
      this.deliveryService.delivered(delivery)
      .then(data => {
        this.loadDeliveries();
        loading.dismiss();
      })
      .catch(error => {
        console.log(error);
        loading.dismiss();
      });
  }
  

  logout() {
    this.loginService.cleanTheToken();
    this.navCtrl.setRoot(Login);
  }
}
