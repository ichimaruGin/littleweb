package com.iwebirth.controller.responsemodel;

import java.util.ArrayList;

/**
 * @author YY_410
 * 2015-1-24
 * Engineering Van's gridtree data model
 * this class is represents the tree node
 * **/
//属性名称要和EVInfoModel.js中的field进行11对应
public class EVTreeResponseLeaf {
	//attr from EVinfo.class
	String tid;  // this attr refers to tid(if its a folder,then it refers to )
	
	String licenseNumber;  
	
	String origin; //EVinfo.getOrigin().getName();

    String owner;
	
	String rentStatus; //EVinfo.getRentStatus().getIsRenting();
	
	/*attr from extjs's prototype*/	
	String iconCls;//node's icon
	
	String qtip;//row's tip
	
	boolean leaf = true;//node is leaf(default is true)

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}


	public String getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public EVTreeResponseLeaf(String tid, String licenseNumber, String origin, String owner, String rentStatus, String iconCls, String qtip, boolean leaf) {
        this.tid = tid;
        this.licenseNumber = licenseNumber;
        this.origin = origin;
        this.owner = owner;
        this.rentStatus = rentStatus;
        this.iconCls = iconCls;
        this.qtip = qtip;
        this.leaf = leaf;
    }
}
