package com.aim.foodtaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "CLIENT_ID")
	private ClientEntity client;
	
	@OneToOne(optional = true, mappedBy="order")
	private DeliveryEntity delivery;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "ORDER_PAYMENT_ID")
	private OrderPaymentEntity payment;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "BRAND_ID")
	private BrandEntity brand;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "SHOP_ID")
	private ShopEntity shop;
	
	@Column(name = "ITEM_DESCRIPTION", nullable=false)
	private String itemDescription;
	
	@Column(name = "ORDER_DATE", nullable=false)
	private Date orderDate;
}
