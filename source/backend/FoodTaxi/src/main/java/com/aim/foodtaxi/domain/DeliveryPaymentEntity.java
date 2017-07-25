package com.aim.foodtaxi.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.PaymentStatus;

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
	
	@Column(name = "DELIVERY_SERVICE_AMOUNT")
	private BigDecimal deliveryServiceAmount;
	
	@Column(name = "DELIVERY_SERVICE_PAYMENT_STAT")
	private PaymentStatus deliveryServicePaymentStatus;
	
	//cash on delivery
	@Column(name = "COD_AMOUNT")
	private BigDecimal codAmount;
	
	@Column(name = "COD_PAYMENT_STAT")
	private PaymentStatus codPaymentStatus;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "INVOICE_ID")
	private InvoiceEntity invoice;
}
