package com.aim.foodtaxi.domain;

import java.math.BigDecimal;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.PaymentStatus;
import com.aim.foodtaxi.enums.PaymentType;

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
	@GeneratedValue(strategy=GenerationType.TABLE, generator="order_payment_seq_gen")
	@SequenceGenerator(name="order_payment_seq_gen", sequenceName = "order_payment_seq", allocationSize = 1, initialValue=50)
	@Column
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private ClientEntity client;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity order;
	
	@Column(name = "TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentType type;
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
}
