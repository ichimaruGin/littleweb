package com.iwebirth.db.service;
/**
 * @author YY_410
 * 2015-1-22
 * ref:http://www.cnblogs.com/happyPawpaw/archive/2013/04/09/3009553.html
 * **/
public enum CRUDEvent {
	
	SAVE_SUCCESS(1),SAVE_FAIL(2),SAVE_EXCEPTION(3),
	QUERY_SUCCESS(4),QUERY_FAIL(5),QUERY_EXCEPTION(6),
	INSERT_SUCCESS(7),INSERT_FAIL(8),INSERT_EXCEPTION(9),
	UPDATE_SUCCESS(10),UPDATE_FAIL(11),UPDATE_EXCEPTION(12),
	REDUPLICATE(13),LOSS_MEMBER(14);	
	
	private int value;
	private CRUDEvent(int value){
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
