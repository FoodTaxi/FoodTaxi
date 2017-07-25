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

    @Column(name = "LONGTITUDE")
    private Double longtitude;

    @Column(name = "LATITUDE")
    private Double latitude;
    
    @Column(name = "ADDRESS_TEXT")
    private String addressText;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @OneToOne
    @JoinColumn(name = "DELIVERY_PAYMENT_ID")
    private DeliveryPaymentEntity deliveryPayment;
    
    @ManyToOne(optional = true)
    private DriverEntity driver;

    @OneToOne(optional = true)
    @JoinColumn(name="BEST_BID_FK") 
    private BidEntity bestBid;
    
    @Column(name = "STATUS")
    private DeliveryStatus status;
    
    @Column(name = "PIN")
    private String pin;
}
