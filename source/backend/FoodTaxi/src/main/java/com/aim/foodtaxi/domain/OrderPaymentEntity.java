package com.aim.foodtaxi.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_ORDER_PAYMENT")
@Getter
@Setter
@ToString
public class OrderPaymentEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private ClientEntity client;
	
	@ManyToOne
	@JoinColumn(name = "DRIVER_ID")
	private DriverEntity driver;
	
	@OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
	private OrderEntity order;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
}
