package com.aim.foodtaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "T_BID")
@Getter
@Setter
@NoArgsConstructor
public class BidEntity {
	@Id
	@SequenceGenerator(name="bid_seq_gen", sequenceName = "bid_seq", allocationSize = 1, initialValue=50)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="bid_seq_gen")
	@Column
	private Long id;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "BID_TIME")
	private Date bidTime;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "DRIVER_ID")
	private DriverEntity driver;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "DELIVERY_ID")
	private DeliveryEntity delivery;
}
