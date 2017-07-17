import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { Storage } from '@ionic/storage';

/*
  Generated class for the LoginService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class LoginService {
  
  public data;
  
  error: string;

  constructor(public http: Http, public authHttp: AuthHttp, private storage: Storage) {
    console.log('Hello LoginService Provider');
  }
  login(username, password) {
  	if (this.data) {
    	// already loaded data
    	return Promise.resolve(this.data);
  	}
    console.log('username ' + username + ' password ' + password);
    var credenials = JSON.stringify({"username": username,"password": password});

  	// don't have the data yet
  	return new Promise(resolve => {
    	// We're using Angular HTTP provider to request the data,
   		// then on the response, it'll map the JSON data to a parsed JS object.
    	// Next, we process the data and resolve the promise with the new data.
    	this.authHttp.post('http://localhost:8080/api/login', credenials)
     	  .subscribe(data => {
      		//Store the token in the storage
          this.storage.set(data.headers.get('authorization'), 'token');
        	this.data = data;
        	resolve(this.data);
      	});
  	});
  }
}
