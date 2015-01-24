package com.iwebirth.util;

import com.iwebirth.db.service.CRUDEvent;

public enum StaticParam {

	//DEPARTMENT_STATUS
	DEPARTMENT_STATUS_EVSELLER(1),
	DEPARTMENT_STATUS_EVUSER(2),
	DEPARTMENT_STATUS_BSSELLER(3),
	DEPARTMENT_STATUS_BSUSER(4),
	
	//EVINFO_KIND
	EV_KIND_TRANSPORT_HEAVY(5),
	EV_KIND_TRANSPORT_LIGHT(6);
	
	private int value;
	private StaticParam(int value){
		this.value = value;
	}
	
	/**
	 * 返回对应的数值
	 * **/
	public int getValue(){
		return value;
	}
	/**
	 * 返回name(调用的是enum的name()方法)
	 * **/
	public String getName(){
		return this.name();
	}

	public static String getNameByValue(int value) {
        for (CRUDEvent c : CRUDEvent.values()) {
            if (c.getValue() == value) {
                return c.name();
            }
        }
        return null;
    }
	
}
