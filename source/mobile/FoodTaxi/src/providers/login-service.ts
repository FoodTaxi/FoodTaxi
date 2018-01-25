import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { Storage } from '@ionic/storage';
import {AppSettings} from '../common/appSettings';

@Injectable()
export class LoginService {
  
  public data;
  
  error: string;

  constructor(public http: Http, public authHttp: AuthHttp, private storage: Storage) {
    console.log('Hello LoginService Provider');
  }

  isTokenSaved() {
    return this.storage.get('token').then(token => {
      if (token && token.length > 0) {
        return true;
      }
             console.log('greda brat');
      return false;
    });
  }

  cleanTheToken() {
     this.storage.set('token', '');
  }

  login(username, password) {
    console.log('username ' + username + ' password ' + password);
    var credenials = JSON.stringify({"username": username,"password": password});

  	// don't have the data yet
  	return new Promise((resolve,reject) => {
    	// We're using Angular HTTP provider to request the data,
   		// then on the response, it'll map the JSON data to a parsed JS object.
    	// Next, we process the data and resolve the promise with the new data.
    	this.authHttp.post(AppSettings.API_ENDPOINT + '/public/login', credenials)
     	  .subscribe(data => {
      		//Store the token in the storage
          this.storage.set(data.headers.get('authorization'), 'token');
          this.storage.set('token', data.headers.get('authorization'));
        	this.data = data;
        	resolve(this.data);
      	}, err => {
          reject(err);
        });
  	});
  }
}
