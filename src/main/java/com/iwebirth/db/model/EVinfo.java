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
	
	@Column(name="LICENSE_NUMBER",unique=true)
	String licenseNumber;  //车牌
	
	@Column(name="KIND")
	String kind; //车辆类型  refer to StaticParam.java

    public Department getOwner() {
        return owner;
    }

    public void setOwner(Department owner) {
        this.owner = owner;
    }

    @ManyToOne(targetEntity=Department.class)
	@Cascade(value={})
	@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="id",nullable=false)
	Department origin;

    @ManyToOne(targetEntity=Department.class)
    @Cascade(value = {})
    @JoinColumn(name="OWNER_ID",referencedColumnName="id",nullable=false)
    Department owner;

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

    public Department getOrigin() {
        return origin;
    }

    public void setOrigin(Department origin) {
        this.origin = origin;
    }

    public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

    public EVinfo() {
    }

    public EVinfo(String tId, String licenseNumber, String kind, Department origin, Department owner) {
        this.tId = tId;
        this.licenseNumber = licenseNumber;
        this.kind = kind;
        this.origin = origin;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "EVinfo{" +
                "id=" + id +
                ", tId='" + tId + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", kind='" + kind + '\'' +
                ", origin=" + origin.getName() +
                ", owner=" + owner.getName() +
                '}';
    }
}
