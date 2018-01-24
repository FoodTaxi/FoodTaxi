import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { AuthHttp } from 'angular2-jwt';
import { Storage } from '@ionic/storage';
import {AppSettings} from '../common/appSettings';

@Injectable()
export class BidService {
  
  constructor(public http: Http, public authHttp: AuthHttp, public storage: Storage) {
  }

  createBid(deliveryId, price) {
   var bid = {"deliveryId": deliveryId,"price": price};
   
    return new Promise(resolve => {
      this.authHttp.post(AppSettings.API_ENDPOINT + '/private/bid/create', bid)
       .subscribe(data => {
          console.log(data);
          resolve(data);
        });
    });
  }
}
