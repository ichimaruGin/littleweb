package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author YY_410
 * 2015-1-24
 * Engineering Van Info
 * **/
@Entity
@Table(name="ev_info")
public class EVinfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="TID",nullable=false,unique=true)
	String tId;  //车辆编号
	
	@Column(name="LICENSE_NUMBER")
	String licenseNumber;  //车牌
	
	@Column(name="TKIND")
	String tKind; //车辆类型  refer to StaticParam.java
	
	@ManyToOne(targetEntity=Department.class)
	@Cascade(value={})
	@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="id",nullable=false)
	Department department;  //所属
	
	@OneToOne(targetEntity=Department.class)
	@Cascade(value={})
	@JoinColumn(name="RENT_STATUS_ID",referencedColumnName="id",nullable=false)
	Integer rentStatusId = 0;  //对应最新的RentStatus (如果值为0，表示当前没有租赁出)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getRentStatusId() {
		return rentStatusId;
	}

	public void setRentStatusId(Integer rentStatusId) {
		this.rentStatusId = rentStatusId;
	}
	
	
}
