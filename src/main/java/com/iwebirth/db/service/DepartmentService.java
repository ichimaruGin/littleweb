package com.iwebirth.db.service;

import com.iwebirth.db.model.EVinfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwebirth.db.model.Department;

import java.util.List;

@Service
public class DepartmentService {
	@Autowired
	SessionFactory sf;
	
	public int addDepartment(Department department){
		Session session = sf.getCurrentSession();
		Department dDepartment = (Department)session.createCriteria(Department.class).add(Restrictions.eq("name", department.getName())).uniqueResult();
		if(dDepartment == null){
			session.save(department);
		}else{
			return CRUDEvent.REDUPLICATE.getValue();  //UNIQUE KEY 重复插入
		}
		return CRUDEvent.INSERT_SUCCESS.getValue();
	}
	
	/**
	 * @param id  which needs update
	 * admin has the authority to do this
	 * **/
	public int updateDepartment(Integer id,Department department){
		int res;
		Session session = sf.getCurrentSession();
		Department dDepartment = (Department)session.createCriteria(Department.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
		if(dDepartment == null){
			res = CRUDEvent.UPDATE_FAIL.getValue();
		}else{
			dDepartment.setName(department.getName());
			dDepartment.setLocation(department.getLocation());
			dDepartment.setLatitude(department.getLatitude());
			dDepartment.setLongitude(department.getLongitude());
			dDepartment.setStatus(department.getStatus()); // status is always fixed
			res = CRUDEvent.UPDATE_SUCCESS.getValue();
		}
		return res;
	}
	
	public Department getDepartmentById(Integer id) {
        Session session = sf.getCurrentSession();
        Department department = (Department) session.get(Department.class, id);
        return department;
    }
}
