package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * @author YY_410
 * 2015-1-24
 * **/
@Entity
@Table(name="rent_status")
public class RentStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;

	@Column(name="TID",nullable=false)
	String tId;
	
	@Column(name="START_TIME")
	Long startTime;
	
	@Column(name="END_TIME")
	Long endTime;
	
	@Column(name="IS_RENTING")
	Boolean isRenting;

	@Column(name="OUT_OF_DATE")
	Boolean outOfDate;
	
	@ManyToOne(targetEntity=Department.class)
	@Cascade(value={})
	@JoinColumn(name="RENTER_ID",referencedColumnName="id")
	Department renter;  //出租方
	
	@ManyToOne(targetEntity=Department.class)
	@Cascade(value={})
	@JoinColumn(name="LESSEER_ID",referencedColumnName="id")
	Department lesseer; //承租方

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

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsRenting() {
		return isRenting;
	}

	public void setIsRenting(Boolean isRenting) {
		this.isRenting = isRenting;
	}

	public Department getRenter() {
		return renter;
	}

	public void setRenter(Department renter) {
		this.renter = renter;
	}

	public Department getLesseer() {
		return lesseer;
	}

	public void setLesseer(Department lesseer) {
		this.lesseer = lesseer;
	}

	public Boolean getOutOfDate() {
		return outOfDate;
	}

	public void setOutOfDate(Boolean outOfDate) {
		this.outOfDate = outOfDate;
	}
	
	
}
