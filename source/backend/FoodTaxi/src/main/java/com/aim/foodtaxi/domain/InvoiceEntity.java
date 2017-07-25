package com.aim.foodtaxi.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.PaymentProvider;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_INVOICE")
@Getter
@Setter
@ToString
public class InvoiceEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
	
	@Column(name = "COD_PAYMENT_PROVIDER")
	private PaymentProvider codPaymentProvider;
	
	@Column(name = "COD_PAYMENT_CODE")
	private String codPaymentCode;
	
	@ManyToOne
	@JoinColumn(name = "DRIVER_ID")
	private DriverEntity driver;
	
	@OneToMany(mappedBy = "invoice")
	private List<DeliveryPaymentEntity> deliveryPayments;
	
	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;
}
