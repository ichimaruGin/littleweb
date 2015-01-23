package com.iwebirth.security;
/**
 * @author YY_410
 * 2015-1-23
 * **/
public class CustomException extends Exception {
	
	private String msg = "This is CustomException";
	public CustomException(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.msg;
	}
	
}
