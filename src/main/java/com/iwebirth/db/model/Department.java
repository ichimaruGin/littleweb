package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	@Id
	@GeneratedValue
	Integer id;
	
	//unique 
	//this attribute is used to query~ eg.'select * from department where name = "SXFJ" '
	@Column(name="NAME",nullable=false,unique=true,columnDefinition="varchar(255) not null")
	String name;
	
	@Column(name="LOCATION",nullable=false,columnDefinition="varchar(255) not null")
	String location;
	
	@Column(name="LATITUDE",nullable=false,columnDefinition="varchar(255) not null")
	String latitude;
	
	@Column(name="LONGITUDE",nullable=false,columnDefinition="varchar(255) not null")
	String longitude;
	
	@Column(name="STATUS",nullable=false)
	String status;  //refer to StaticParam.java

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Department(){}
	public Department(String name, String location,
			String latitude, String longitude, String status) {
		super();
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
	}
	
}
