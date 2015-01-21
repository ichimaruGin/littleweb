package com.iwebirth.controller.responsemodel;

import java.io.Serializable;

public class LoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1656082078045656966L;
	
	String username;
	
	String password;
	
	String authcode;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public void show(){
		System.out.println("username:"+username+" "+"password:"+password+" authcode:"+authcode);
	}
	
}
