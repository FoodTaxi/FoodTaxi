import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { AuthHttp } from 'angular2-jwt';
import { Storage } from '@ionic/storage';
import {AppSettings} from '../common/appSettings';

@Injectable()
export class DriverService {
  
  constructor(public http: Http, public authHttp: AuthHttp, public storage: Storage) {
  }

  getProfile() {
    return new Promise(resolve => {
      this.authHttp.get(AppSettings.API_ENDPOINT + '/private/driver/me')
       .map(res => res.json())
       .subscribe(data => {
          console.log(data);
          console.log("in driver me -->");
          resolve(data);
        });
    });
  }
}
