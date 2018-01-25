import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Geolocation } from 'ionic-native';

import {
 GoogleMaps,
 GoogleMap,GoogleMapsEvent, GoogleMapOptions
} from '@ionic-native/google-maps';

declare var google;
/*
  Generated class for the Map page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-map',
  templateUrl: 'map.html'
})
export class MapPage {
  map: GoogleMap;
   
  public longitude: any;
  public latitude: any;
  public nameOfPoint: any;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  	this.latitude = navParams.get('latitude');
  	this.longitude = navParams.get('longitude');
  	this.nameOfPoint = navParams.get('nameOfPoint');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MapPage');
    this.loadMap();
  }

   loadMap() {

    Geolocation.getCurrentPosition().then((position) => {
      console.log('getCurrentPosition');
      console.log(position);
      let latLng = new google.maps.LatLng(this.latitude, this.longitude);
 
  
     let mapOptions: GoogleMapOptions = {
      camera: {
        target: latLng,
        zoom: 15,
        tilt: 30
      }};

      this.map = GoogleMaps.create('map', mapOptions);
     //this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

    // Wait the MAP_READY before using any methods.
    this.map.one(GoogleMapsEvent.MAP_READY)
      .then(() => {
        console.log('Map is ready!');

        // Now you can use all methods safely.
        this.map.addMarker({
            title: 'Къде съм аз',
            icon: 'blue',
            animation: 'DROP',
            position: {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            }
          })
          .then(marker => {
            marker.on(GoogleMapsEvent.MARKER_CLICK)
              .subscribe(() => {
                alert('clicked');
              });
          });

          this.map.addMarker({
            title: this.nameOfPoint,
            icon: 'green',
            animation: 'DROP',
            position: {
              lat: this.latitude,
              lng: this.longitude
            }
          });


      });
    });
  }
}
