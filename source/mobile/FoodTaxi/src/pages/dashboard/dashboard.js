var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { BidService } from '../../providers/bid-service';
import { OrderService } from '../../providers/order-service';
import { Order } from '../order/order';
var Dashboard = (function () {
    function Dashboard(navCtrl, orderService) {
        this.navCtrl = navCtrl;
        this.orderService = orderService;
        this.orders = [];
        this.loadOrders();
    }
    Dashboard.prototype.loadOrders = function () {
        var _this = this;
        this.orderService.load()
            .then(function (data) {
            _this.orders = data;
        });
    };
    Dashboard.prototype.openOrder = function (order) {
        // That's right, we're pushing to ourselves!
        this.navCtrl.push(Order, {
            order: order
        });
    };
    return Dashboard;
}());
Dashboard = __decorate([
    Component({
        selector: 'page-dashboard',
        templateUrl: 'dashboard.html',
        providers: [BidService, OrderService]
    }),
    __metadata("design:paramtypes", [NavController, OrderService])
], Dashboard);
export { Dashboard };
//# sourceMappingURL=dashboard.js.map