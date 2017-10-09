import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Geolocation } from 'ionic-native';

declare var google;

@Component({
  selector: 'page-order',
  templateUrl: 'delivery.html'
})
export class Delivery {
	
  @ViewChild('map') mapElement: ElementRef;
  map: any;
 
  delivery: any;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    // If we navigated to this page, we will have an item available as a nav param
    this.delivery = navParams.get('order');
    this.loadMap();
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
