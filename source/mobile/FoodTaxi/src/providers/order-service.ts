import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { AuthHttp } from 'angular2-jwt';

/*
  Generated class for the OrderService provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class OrderService {
  
  public data;
  constructor(public http: Http, public authHttp: AuthHttp) {
    console.log('Hello OrderService Provider');
  }
  load() {
  	if (this.data) {
    	// already loaded data
    	return Promise.resolve(this.data);
  	}

  	// don't have the data yet
  	return new Promise(resolve => {

    	// We're using Angular HTTP provider to request the data,
   		// then on the response, it'll map the JSON data to a parsed JS object.
    	// Next, we process the data and resolve the promise with the new data.
    	this.authHttp.get('http://localhost:8080/api/openOrders')
      	.map(res => res.json())
     	.subscribe(data => {
      		console.log('Data --- > ');
      		console.log(data);
        	// we've got back the raw data, now generate the core schedule data
        	// and save the data for later reference
        	this.data = data;
        	resolve(this.data);
      	});
  	});
  }
}
