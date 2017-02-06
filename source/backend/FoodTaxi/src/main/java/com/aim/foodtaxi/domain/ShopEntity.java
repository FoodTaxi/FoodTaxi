package com.aim.foodtaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_SHOP")
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LONGTITUDE")
    private Double longtitude;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "ORDERS_PHONE")
    private String ordersPhone;

    @Column(name = "ORDERS_EMAIL")
    private String ordersEmail;

    @Column(name = "ORDERS_SMS")
    private String ordersSMS;

    @ManyToOne
    private BrandEntity brand;

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

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
