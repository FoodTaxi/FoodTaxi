package com.aim.foodtaxi.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy=GenerationType.TABLE, generator="delivery_seq_gen")
    @SequenceGenerator(name="delivery_seq_gen", sequenceName = "delivery_seq", allocationSize = 1, initialValue=50)
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
    
    @Column(name = "PICKUP_DUE_DATE")
    private Date pickupDueDate;
    
    @Column(name = "PICKEDUP_DATE")
    private Date pickedupDate;
    
    @OneToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @OneToMany(mappedBy = "delivery", fetch=FetchType.LAZY)
    private List<DeliveryPaymentEntity> deliveryPayments;
    
    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "DRIVER_ID")
    private DriverEntity driver;

    @OneToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name="BEST_BID_ID") 
    private BidEntity bestBid;
    
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    
    @Column(name = "HAS_COD")
    private boolean hasCod;
    
    @Column(name = "COD_AMOUNT")
    private BigDecimal codAmount;
    
    @Column(name = "PIN")
    private String pin;
    
    @Column(name = "ORDER_VALUE", nullable=false)
    private BigDecimal orderValue;
}
