import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { AuthHttp } from 'angular2-jwt';
import { Storage } from '@ionic/storage';
import {AppSettings} from '../common/appSettings';

@Injectable()
export class DeliveryService {
  
  constructor(public http: Http, public authHttp: AuthHttp, public storage: Storage) {
  }
  delivered(delivery) {
    var delivered = {
      "delivered": true,
      "deliveryId": delivery.id,
      "denialReason": ""
    };
    return new Promise(resolve => {

      this.authHttp.post(AppSettings.API_ENDPOINT + '/private/delivery/complete', delivered)
       .subscribe(data => {
          console.log(data);
          resolve(data);
        }, err => {
          console.log(err);
        });
    });
  }
  getOpenDeliveries() {
  	return new Promise(resolve => {
    	this.authHttp.get(AppSettings.API_ENDPOINT + '/private/delivery/driver')
      .map(res => res.json())
     	.subscribe(data => {
         console.log(data);
        	resolve(data);
      	}, err => {
          console.log(err);
        });
  	});
  }
}
