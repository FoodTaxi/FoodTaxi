package com.aim.foodtaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private OrderStatus status;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "CLIENT_ID")
	private ClientEntity client;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "DELIVERY_ID")
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
	
	@Column(name = "ITEM_DESCRIPTION")
	private String itemDescription;
}
