package com.iwebirth.controller.responsemodel;

import java.io.Serializable;

public class LoginStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2930950783348870020L;
	
	public  static final String ADMIN = "admin";
	public  static final String EV_SELLER = "ev_seller";
	public  static final String EV_USER = "ev_user";
	public  static final String BS_SELLER = "ev_seller";
	public  static final String BS_USER = "ev_user";
	public  static final String PEOPLE = "people";
	
	String username;
	
	String level;
	
	Boolean alive;

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

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	
	public LoginStatus(String username,String level,boolean alive){
		this.username = username;
		this.level = level;
		this.alive = alive;
	}
}
