package com.aim.foodtaxi.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_DELIVERY")
@Getter
@Setter
@ToString
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_LONGTITUDE")
    private Double startLongtitude;

    @Column(name = "START_LATITUDE")
    private Double startLatitude;
    
    @Column(name = "START_ADDRESS_TEXT")
    private String startAddressText;

    @Column(name = "END_LONGTITUDE")
    private Double endLongtitude;

    @Column(name = "END_LATITUDE")
    private Double endLatitude;
    
    @Column(name = "END_ADDRESS_TEXT")
    private String endAddressText;
    
    @Column(name = "START_DATE")
    private Date startDate;
    
    @Column(name = "END_DATE")
    private Date endDate;
    
    @Column(name = "DUE_DATE")
    private Date dueDate;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @OneToMany(mappedBy = "delivery")
    private List<DeliveryPaymentEntity> deliveryPayments;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "DRIVER_ID")
    private DriverEntity driver;

    @OneToOne(optional = true)
    @JoinColumn(name="BEST_BID_ID") 
    private BidEntity bestBid;
    
    @Column(name = "STATUS")
    private DeliveryStatus status;
    
    @Column(name = "HAS_COD")
    private boolean hasCod;
    
    @Column(name = "COD_AMOUNT")
    private BigDecimal codAmount;
    
    @Column(name = "PIN")
    private String pin;
}
