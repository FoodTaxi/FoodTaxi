import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { BidService }  from  '../../providers/bid-service'

@Component({
  selector: 'page-delivery',
  templateUrl: 'delivery.html',
  providers: [BidService]
})
export class Delivery {  

 
  public delivery: any;
  public bidPrice: any;

  constructor( public navCtrl: NavController, public navParams: NavParams, public bidService: BidService) {
    // If we navigated to this page, we will have an item available as a nav param
    this.delivery = navParams.get('delivery');
    this.bidPrice = this.delivery.bestBidAmount - 0.10;
    this.bidPrice = this.bidPrice.toFixed(2);
  }



}
