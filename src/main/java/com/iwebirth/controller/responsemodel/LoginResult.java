package com.iwebirth.controller.responsemodel;

public class LoginResult {
	
	String username;
	
//	SUCCESS
//	FAIL_AUTHCODE
//	FAIL_USER_ERROR
//	FAIL_PASSWD_ERROR
	String result;

	boolean success = true;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
