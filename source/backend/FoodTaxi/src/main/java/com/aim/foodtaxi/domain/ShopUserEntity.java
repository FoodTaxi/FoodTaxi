package com.aim.foodtaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "T_SHOP_USER")
@Getter
@Setter
@NoArgsConstructor
public class ShopUserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="shop_user_seq_gen")
	@SequenceGenerator(name="shop_user_seq_gen", sequenceName = "shop_user_seq", allocationSize = 1, initialValue=50)
	@Column
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="REGISTER_DATE")
	private Date registerDate;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_ID")
	private ClientEntity client;
}
