package com.aim.foodtaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ORDER")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PAYMENT_TYPE")
    private String paymnentType;
    
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LONGTITUDE")
    private Double longtitude;

    @Column(name = "LATITUDE")
    private Double latitude;
    
    @Column(name = "ADDRESS_TEXT")
    private String addressText;

    @Column(name = "SHIPMENT_DATE")
    private Date shipmentDate;

    @ManyToOne(optional = true)
    private DriverEntity driver;

    @ManyToOne(optional = true)
    private ShopEntity shop;

    @ManyToOne(optional = true)
    private BrandEntity brand;

    @OneToOne
    @JoinColumn(name="BEST_BID_FK") 
    private BidEntity bestBid;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymnentType() {
        return paymnentType;
    }

    public void setPaymnentType(String paymnentType) {
        this.paymnentType = paymnentType;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
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

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public BidEntity getBestBid() {
        return bestBid;
    }

    public void setBestBid(BidEntity bestBid) {
        this.bestBid = bestBid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }
}
