import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController, NavParams, LoadingController } from 'ionic-angular';
import { Geolocation } from 'ionic-native';
import { BidService }  from  '../../providers/bid-service'


declare var google;

@Component({
  selector: 'page-delivery',
  templateUrl: 'delivery.html',
  providers: [BidService]
})
export class Delivery {
	
  @ViewChild('map') mapElement: ElementRef;
  map: any;
 
  public delivery: any;
  public bidPrice: any;

  constructor(public navCtrl: NavController, public loadingCtrl: LoadingController, public navParams: NavParams, public bidService: BidService) {
    // If we navigated to this page, we will have an item available as a nav param
    this.delivery = navParams.get('delivery');
    this.bidPrice = 5.50;
    this.loadMap();
  }

  makeABid() {
    let loading = this.loadingCtrl.create({
      content: 'Please wait...'
    });
    loading.present();
    this.bidService.createBid(this.delivery.id, this.bidPrice).then(data => {
      loading.dismiss();
    });;
  } 


  ionViewLoaded(){
    this.loadMap();
  }
 
  loadMap(){
    console.log('load map');
 	  Geolocation.getCurrentPosition().then((position) => {
      console.log('getCurrentPosition');
      console.log(position);
      let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
 
      let mapOptions = {
        center: latLng,
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      }
      console.log('map');
      // console.log(this.map);
      // console.log(this.mapElement);
      this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
      console.log('map');
      // console.log(this.map);
    }, (err) => {
      console.log('error- >>> ')
      console.log(err);
    });
  }

}
