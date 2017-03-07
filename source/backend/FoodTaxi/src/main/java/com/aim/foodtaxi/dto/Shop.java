package com.aim.foodtaxi.dto;

public class Shop {

    private Long id;
    private String name;
    private Double longtitude;
    private Double latitude;
    private String ordersPhone;
    private String ordersEmail;
    private String ordersSMS;
    private String addressText;

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

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }
}
