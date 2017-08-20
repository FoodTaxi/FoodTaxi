package com.aim.foodtaxi.domain;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.OrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_ORDER")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column(name = "ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID")
	private ClientEntity client;
	
	@OneToOne(optional = true, mappedBy="order", fetch=FetchType.LAZY)
	private DeliveryEntity delivery;
	
	@OneToOne(optional = true, fetch=FetchType.LAZY)
	@JoinColumn(name = "ORDER_PAYMENT_ID")
	private OrderPaymentEntity payment;
	
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "BRAND_ID")
	private BrandEntity brand;
	
	@ManyToOne(optional = true, fetch=FetchType.LAZY)
	@JoinColumn(name = "SHOP_ID")
	private ShopEntity shop;
	
	@Column(name = "ITEM_DESCRIPTION", nullable=false)
	private String itemDescription;
	
	@Column(name = "ORDER_DATE", nullable=false)
	private Date orderDate;
	
	@Column(name = "ORDER_VALUE", nullable=false)
    private BigDecimal orderValue;
}
