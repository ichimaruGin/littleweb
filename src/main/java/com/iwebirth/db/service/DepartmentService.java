package com.iwebirth.db.service;

import com.iwebirth.db.model.Department;
import com.iwebirth.db.service.common.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
    CommonDao commonDao;

	public int addDepartment(Department department){
//		Session session = sf.getCurrentSession();
//		Department dDepartment = (Department)session.createCriteria(Department.class).add(Restrictions.eq("name", department.getName())).uniqueResult();
//		if(dDepartment == null){
//			session.save(department);
//		}else{
//			return CRUDEvent.REDUPLICATE.getValue();  //UNIQUE KEY 重复插入
//		}
//		return CRUDEvent.INSERT_SUCCESS.getValue();
        return commonDao.insertSingleObject(department);
	}
}
