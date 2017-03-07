package com.aim.foodtaxi.dto;

import java.util.Date;

public class Order {

    private Long id;
    private Double price;
    private String paymnentType;
    private Double longtitude;
    private Double latitude;
    private Date shipmentDate;
    private Long brandId; 
    private Bid bestBid;
    private String description;
    private String addressText;
    private String brandLogoPath;
    private String shopAddressText;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPaymnentType() {
        return paymnentType;
    }

    public void setPaymnentType(String paymnentType) {
        this.paymnentType = paymnentType;
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Bid getBestBid() {
        return bestBid;
    }

    public void setBestBid(Bid bestBid) {
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

    public String getBrandLogoPath() {
        return brandLogoPath;
    }

    public void setBrandLogoPath(String brandLogoPath) {
        this.brandLogoPath = brandLogoPath;
    }

    public String getShopAddressText() {
        return shopAddressText;
    }

    public void setShopAddressText(String shopAddressText) {
        this.shopAddressText = shopAddressText;
    }

}
