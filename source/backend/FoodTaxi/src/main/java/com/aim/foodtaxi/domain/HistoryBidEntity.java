package com.aim.foodtaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TH_BID")
@Getter
@Setter
@ToString
public class HistoryBidEntity {
    @Id
    @Column
    private Long id;
    
    @Column(name = "PRICE")
	private Double price;

	@Column(name = "BID_TIME")
	private Date bidTime;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;
	
	@Column(name = "IS_WINNING")
	private boolean isWinning;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "DRIVER_ID")
	private DriverEntity driver;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "TH_DELIVERY_ID")
	private HistoryDeliveryEntity delivery;
}
