package com.aim.foodtaxi.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.PaymentStatus;
import com.aim.foodtaxi.enums.PaymentType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_DELIVERY_PAYMENT")
@Getter
@Setter
@ToString
public class DeliveryPaymentEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "DELIVERY_ID")
	private DeliveryEntity delivery;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private PaymentType type;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "INVOICE_ID")
	private InvoiceEntity invoice;
}
