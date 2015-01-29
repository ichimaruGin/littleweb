package com.iwebirth.controller.responsemodel;
/**
 * @author YY_410
 * 2015-1-22
 * **/
public class LoginResponse {
		
	String username;
	
	String result;
	
	String level;  //first check User.level ,then if User.level == "normal",then check the User.department.status
	
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
