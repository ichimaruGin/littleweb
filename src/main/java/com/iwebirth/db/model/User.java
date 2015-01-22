package com.iwebirth.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="USERNAME",nullable=false,unique=true,columnDefinition="varchar(255) not null")
	String username;
	
	@Column(name="PASSWORD",nullable=false,columnDefinition="varchar(255) not null")
	String password;
	
	@Column(name="LEVEL",nullable=false,columnDefinition="varchar(255) not null")
	String level;
	
	@ManyToOne(targetEntity=Department.class)
	@Cascade(value={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.SAVE_UPDATE}) //可级联插入
	@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="id")
	Department department;
	
	@Column	
	Long registerTime = System.currentTimeMillis();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}
	public User(){}
	public User(String username, String password, String level,
			Department department, Long registerTime) {
		super();
		this.username = username;
		this.password = password;
		this.level = level;
		this.department = department;
		this.registerTime = registerTime;
	}
	
	
}
