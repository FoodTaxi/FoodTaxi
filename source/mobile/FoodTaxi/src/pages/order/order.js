var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Geolocation } from 'ionic-native';
var Order = (function () {
    function Order(navCtrl, navParams) {
        this.navCtrl = navCtrl;
        this.navParams = navParams;
        // If we navigated to this page, we will have an item available as a nav param
        this.order = navParams.get('order');
        this.loadMap();
    }
    Order.prototype.ionViewLoaded = function () {
        this.loadMap();
    };
    Order.prototype.loadMap = function () {
        var _this = this;
        console.log('load map');
        Geolocation.getCurrentPosition().then(function (position) {
            console.log('getCurrentPosition');
            console.log(position);
            var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            var mapOptions = {
                center: latLng,
                zoom: 15,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            console.log('map');
            // console.log(this.map);
            // console.log(this.mapElement);
            _this.map = new google.maps.Map(_this.mapElement.nativeElement, mapOptions);
            console.log('map');
            // console.log(this.map);
        }, function (err) {
            console.log('error- >>> ');
            console.log(err);
        });
    };
    return Order;
}());
__decorate([
    ViewChild('map'),
    __metadata("design:type", ElementRef)
], Order.prototype, "mapElement", void 0);
Order = __decorate([
    Component({
        selector: 'page-order',
        templateUrl: 'order.html'
    }),
    __metadata("design:paramtypes", [NavController, NavParams])
], Order);
export { Order };
//# sourceMappingURL=order.js.map