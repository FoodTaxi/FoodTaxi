import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { AuthHttp } from 'angular2-jwt';
import { Storage } from '@ionic/storage';
import {AppSettings} from '../common/appSettings';

@Injectable()
export class DeliveryService {
  
  public data;
  constructor(public http: Http, public authHttp: AuthHttp, public storage: Storage) {
  }
  getOpenDeliveries() {
  	if (this.data) {
    	// already loaded data
    	return Promise.resolve(this.data);
  	}
  	return new Promise(resolve => {
    	this.authHttp.get(AppSettings.API_ENDPOINT + '/private/delivery/driver')
      .map(res => res.json())
     	.subscribe(data => {
        	this.data = data;
        	resolve(this.data);
      	}, err => {
          console.log(err);
        });
  	});
  }
}
