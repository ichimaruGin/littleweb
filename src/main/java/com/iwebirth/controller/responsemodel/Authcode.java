package com.iwebirth.controller.responsemodel;

import java.io.Serializable;

public class Authcode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939884081069401345L;

	String content;
	
	Long createTimeInMillis;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getCreateTimeInMillis() {
		return createTimeInMillis;
	}

	public void setCreateTimeInMillis(Long createTimeInMillis) {
		this.createTimeInMillis = createTimeInMillis;
	}

	public Authcode(String content,Long createTimeInMillis){
		this.content = content;
		this.createTimeInMillis = createTimeInMillis;
	}
}
