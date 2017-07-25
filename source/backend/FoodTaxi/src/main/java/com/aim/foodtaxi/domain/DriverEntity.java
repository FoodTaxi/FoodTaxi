package com.aim.foodtaxi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.DriverStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_DRIVER")
@Getter
@Setter
@NoArgsConstructor
public class DriverEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(name="DRIVER_STATUS")
	private DriverStatus status;
	
	@Column(name="CAB_NUMBER") 
	private String cabNumber;
	
	@Column(name="REG_NO")
	private String regNo;
	
	@Column(name="RATING")
	private Integer rating;
	
	@Column(name="CURRENT_STATUS")
	private String currentStatus;
	
	@Column(name="IMAGE_PATH")
    private String imagePath;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="driver")
	List<DeliveryEntity> deliveries;
}
