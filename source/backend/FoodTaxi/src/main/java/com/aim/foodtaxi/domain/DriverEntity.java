package com.aim.foodtaxi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.aim.foodtaxi.enums.DriverAccountStatus;
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
	@GeneratedValue(strategy=GenerationType.TABLE, generator="driver_seq_gen")
	@SequenceGenerator(name="driver_seq_gen", sequenceName = "driver_seq", allocationSize = 1, initialValue=50)
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
	@Enumerated(EnumType.STRING)
	private DriverAccountStatus status;
	
	@Column(name="CAB_NUMBER") 
	private String cabNumber;
	
	@Column(name="CAB_PLATE_NUMBER")
	private String cabPlateNumber;
	
	@Column(name="RATING")
	private Integer rating;
	
	@Column(name="CURRENT_STATUS")
	@Enumerated(EnumType.STRING)
	private DriverStatus currentStatus;
	
	@Column(name="IMAGE_PATH")
    private String imagePath;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="driver")
	List<DeliveryEntity> deliveries;
}
