package com.iwebirth.db.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwebirth.controller.responsemodel.LoginInfo;
import com.iwebirth.controller.responsemodel.LoginResponse;
import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.User;

@Component
public class UserService {
	@Autowired
	SessionFactory sf;
	
	/**
	 * User login check service(contains username & password check)
	 * Authcode check is in LoginController
	 * **/
	public LoginResponse checkUser(LoginInfo loginInfo){
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUsername(loginInfo.getUsername());
		try{
			Session session = sf.getCurrentSession();
			Criteria c = session.createCriteria(User.class).add(Restrictions.eq("username", loginInfo.getUsername())).setMaxResults(1);
			User dUser = (User)c.uniqueResult();
			if(dUser == null){				
				loginResponse.setResult(LoginResponse.FAIL_USER_ERROR); //has no such username
			}else{
				if(!dUser.getPassword().equals(loginInfo.getPassword()))
					loginResponse.setResult(LoginResponse.FAIL_PASSWD_ERROR); //passwd error
				else{
					loginResponse.setLevel(dUser.getLevel());   //pass check && add user level
					loginResponse.setResult(LoginResponse.SUCCESS);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(loginResponse.getResult());
		return loginResponse;
	}
	
	public int addUser(User user){
		int res = 0;
		Department department = user.getDepartment();
		try{
			Session session = sf.getCurrentSession();
			Criteria c = session.createCriteria(Department.class).add(Restrictions.eq("name", department.getName())).setReadOnly(true);
			Department dDepartment = (Department)c.uniqueResult();
			if(dDepartment == null){
				//table department doesn't have this department
				session.save(user);  //save User ，meanwhile cascade save Department 
			}else{
				//user old department
				user.setDepartment(dDepartment);
				session.save(user);
			}
			res = CRUDEvent.INSERT_SUCCESS.getValue();
		}catch(Exception e){
			res = CRUDEvent.INSERT_EXCEPTION.getValue();
			e.printStackTrace();
		}
		System.out.println("addUser res:"+res);
		return res;
	}
}
