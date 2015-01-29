package com.iwebirth.db.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.EVinfo;

@Service
public class EVinfoService {
	@Autowired
	SessionFactory sf;
	
	//对于Evinfo的department属性，认为它已经存在
	//合理的操作是先从Department表中查到一个对象，然后把这个department放到ev中
	//不要通过此操作同时插入department
	public int addEVinfo(EVinfo ev){
		int res = 0;
		Session session = sf.getCurrentSession();
		Criteria c = session.createCriteria(EVinfo.class).add(Restrictions.eq("tId", ev.gettId()));
		EVinfo dEv = (EVinfo)c.uniqueResult();
		if(dEv != null){
			res = CRUDEvent.REDUPLICATE.getValue();
		}else{
			Department dDepartment = (Department)session.createCriteria(Department.class).add(Restrictions.eq("name", ev.getDepartment().getName())).uniqueResult(); //查看department是否已经存在
			if(dDepartment != null){
				ev.setDepartment(dDepartment); //使用持久化的department
				session.save(ev);
				res = CRUDEvent.INSERT_SUCCESS.getValue();
			}else{
				System.out.println("请先完成department的插入");
				res = CRUDEvent.LOSS_MEMBER.getValue();
			}			
		}
		return res;
	}
	/**
	 * 批量插入(要求department相同)
	 * **/
	public int addEVinfo(List<EVinfo> evs){
		int res = 0;
		Session session = sf.getCurrentSession();
		Department dDepartment = (Department)session.createCriteria(Department.class).add(Restrictions.eq("name", evs.get(0).getDepartment().getName())).uniqueResult();
		if(dDepartment != null){
			for(EVinfo ev :evs){
				ev.setDepartment(dDepartment);
				session.save(ev);
				res = CRUDEvent.INSERT_SUCCESS.getValue();
			}
		}else{
			System.out.println("请先完成department的插入");
			res = CRUDEvent.LOSS_MEMBER.getValue();
		}
		return res;
	}
}
