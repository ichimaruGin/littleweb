package com.iwebirth.db.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "user_level", nullable = false)
    String userLevel;  // admin&department&personal

//	@ManyToOne(targetEntity=Department.class)
//	@Cascade(value={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.SAVE_UPDATE}) //可级联插入
//	@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="id")
//	Department department;

    @Column(name = "department_id")
    Integer departmentId;

    //时间为s级
    @Column(name = "register_time", nullable = false)
    Integer registerTime = (int)(System.currentTimeMillis() / 1000);

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Integer registerTime) {
        this.registerTime = registerTime;
    }

    public User(){}

    public User(String username, String password, String userLevel, Integer departmentId, Integer registerTime) {
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
        this.departmentId = departmentId;
        this.registerTime = registerTime;
    }
}
