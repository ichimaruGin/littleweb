package com.iwebirth.controller.responsemodel;

import java.io.Serializable;

public class UserStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2930950783348870020L;
	
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
	
	public UserStatus(String username,String level,boolean alive){
		this.username = username;
		this.level = level;
		this.alive = alive;
	}
}
