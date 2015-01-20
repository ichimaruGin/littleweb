package com.iwebirth.db.model;

public class DefaultUser {
	String username;
	String password;
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
	
	public DefaultUser(String usernmae,String password){
		this.username = usernmae;
		this.password = password;
	}
}
