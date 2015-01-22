package com.iwebirth.controller.responsemodel;
/**
 * @author YY_410
 * 2015-1-22
 * **/
public class LoginResponse {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL_AUTHCODE = "FAIL_AUTHCODE";
	public static final String FAIL_USER_ERROR = "FAIL_USER_ERROR";
	public static final String FAIL_PASSWD_ERROR = "FAIL_PASSWD_ERROR";	
	
	String username;
	
	String result;
	
	String level;
	
	boolean success = true;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
