package com.aim.foodtaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SHOP")
@Getter
@Setter
@NoArgsConstructor
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="shop_seq_gen")
    @SequenceGenerator(name="shop_seq_gen", sequenceName = "shop_seq", allocationSize = 1, initialValue=50)
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
    
    @Column(name = "ADDRESS_TEXT")
    private String addressText;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private BrandEntity brand;
    
}
