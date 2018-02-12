import { Component } from '@angular/core';
import { NavParams, ViewController } from 'ionic-angular';
import { BidService }  from  '../../providers/bid-service'

@Component({
  selector: 'page-delivery',
  templateUrl: 'delivery.html',
  providers: [BidService]
})
export class Delivery {  

 
  public delivery: any;
  public bidPrice: any;

  constructor( public navParams: NavParams, public bidService: BidService, public viewCtrl: ViewController) {
    // If we navigated to this page, we will have an item available as a nav param
    this.delivery = navParams.get('delivery');
    this.bidPrice = this.delivery.bestBidAmount - 0.10;
    this.bidPrice = this.bidPrice.toFixed(2);
  }

  twoDigits(number) {
    if (number > -10  && number <10) {
      return "0" + number;
    } 
    return "" + number;
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

  closeModal() {
    this.viewCtrl.dismiss();
  }

}
