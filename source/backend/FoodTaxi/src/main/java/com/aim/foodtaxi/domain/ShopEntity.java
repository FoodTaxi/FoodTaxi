package com.aim.foodtaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_SHOP")
public class ShopEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="LONGTITUDE")
    private String longtitude;
    
    @Column(name="LATITUDE")
    private String latitude;
    
    @Column(name="ORDERS_PHONE")
    private String ordersPhone;
    
    @Column(name="ORDERS_EMAIL")
    private String ordersEmail;
    
    @Column(name="ORDERS_SMS")
    private String ordersSMS;
    
    @ManyToOne
    private BrandEntity brand;

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOrdersPhone() {
        return ordersPhone;
    }

    public void setOrdersPhone(String ordersPhone) {
        this.ordersPhone = ordersPhone;
    }

    public String getOrdersEmail() {
        return ordersEmail;
    }

    public void setOrdersEmail(String ordersEmail) {
        this.ordersEmail = ordersEmail;
    }

    public String getOrdersSMS() {
        return ordersSMS;
    }

    public void setOrdersSMS(String ordersSMS) {
        this.ordersSMS = ordersSMS;
    }
}
