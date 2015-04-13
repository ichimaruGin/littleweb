package com.iwebirth.db.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EVinfoService {
	@Autowired
	SessionFactory sf;

	//对于Evinfo的department属性，认为它已经存在
	//合理的操作是先从Department表中查到一个对象，然后把这个department放到ev中
	//不要通过此操作同时插入department
//	public int addEVinfo(EVinfo ev){
//		int res;
//		Session session = sf.getCurrentSession();
//		Criteria c = session.createCriteria(EVinfo.class).add(Restrictions.eq("tId", ev.gettId()));
//		EVinfo dEv = (EVinfo)c.uniqueResult();
//		if(dEv != null){
//			res = CRUDEvent.REDUPLICATE.getValue();
//		}else{
//			Department dOwner = (Department)session.createCriteria(Department.class).add(Restrictions.eq("name", ev.getOwner().getName())).uniqueResult(); //查看department是否已经存在
//			Department dOrigin = (Department)session.createCriteria(Department.class).add(Restrictions.eq("name", ev.getOwner().getName())).uniqueResult();
//            if(dOwner != null && dOrigin != null){
//				ev.setOwner(dOwner); //使用持久化的department
//                ev.setOrigin(dOrigin);
//				session.save(ev);
//				res = CRUDEvent.INSERT_SUCCESS.getValue();
//			}else{
//				System.out.println("请先完成department的插入");
//				res = CRUDEvent.LOSS_MEMBER.getValue();
//			}
//		}
//		return res;
//	}
//
//    //获得某个用户名对应的公司的所有工程车
//    public List<EVinfo> getEVinfoByUser(String userName){
//        Session session = sf.getCurrentSession();
//        Criteria c = session.createCriteria(User.class).add(Restrictions.eq("username",userName));
//        c.setFetchMode("department", FetchMode.JOIN);
//        User dUser = (User)c.uniqueResult();
//        Department dDepartment = dUser.getDepartment();
//        List<EVinfo> evs = new ArrayList<EVinfo>();
//        if(dDepartment != null){
//            if(dDepartment.getStatus().equals(StaticParam.DEPARTMENT_STATUS_EV_SELLER)) {
//                c = session.createCriteria(EVinfo.class).add(Restrictions.eq("origin", dDepartment)).setReadOnly(true);
//                c.setFetchMode("origin",FetchMode.JOIN);
//            }else if(dDepartment.getStatus().equals(StaticParam.DEPARTMENT_STATUS_EV_BUYER)) {
//                c = session.createCriteria(EVinfo.class).add(Restrictions.eq("owner", dDepartment)).setReadOnly(true);
//                c.setFetchMode("owner",FetchMode.JOIN);
//            }
//            evs = c.list();
//        }else{
//            System.out.println("dDepartment == null");
//        }
//        return evs;
//    }
//
//    //获得某个用户名对应的公司的所有工程车
//    public Map<String,List> getEVinfoAsTreeStore(String username){
//        Map<String,List> map = new HashMap<String, List>();
//        Session session = sf.getCurrentSession();
//        Criteria c = session.createCriteria(User.class).add(Restrictions.eq("username",username));
//        c.setFetchMode("department", FetchMode.JOIN);
//        User dUser = (User)c.uniqueResult();
//        Department dDepartment  = dUser.getDepartment();
//        if(dDepartment != null){
//            if(dDepartment.getStatus().equals(StaticParam.DEPARTMENT_STATUS_EV_SELLER)) {
//                c = session.createCriteria(EVinfo.class).add(Restrictions.eq("origin", dDepartment)).setReadOnly(true);
//                c.setFetchMode("origin",FetchMode.JOIN);
//                c.setProjection(Projections.projectionList()
//                        .add(Projections.groupProperty("kind")));
//                List l_kind = c.list();  //get kinds
//                if(l_kind.size()>0){
//                    for(Iterator<String> iter = l_kind.iterator();iter.hasNext();){
//                        String kind = iter.next();
//                        c = session.createCriteria(EVinfo.class).add(Restrictions.eq("origin", dDepartment))
//                                .add(Restrictions.eq("kind",kind)).setReadOnly(true);
//                        c.setFetchMode("origin",FetchMode.JOIN);
//                        List<EVinfo> evs = c.list();  //get evinfos by kind
//                        map.put(kind,evs);
//                    }
//                }
//            }else if(dDepartment.getStatus().equals(StaticParam.DEPARTMENT_STATUS_EV_BUYER)) {
//                c = session.createCriteria(EVinfo.class).add(Restrictions.eq("owner", dDepartment)).setReadOnly(true);
//                c.setFetchMode("owner",FetchMode.JOIN);
//                c.setProjection(Projections.projectionList()
//                        .add(Projections.groupProperty("kind")));
//                List l_kind = c.list();  //get kinds
//                if(l_kind.size()>0){
//                    for(Iterator<String> iter = l_kind.iterator();iter.hasNext();){
//                        String kind = iter.next();
//                        c = session.createCriteria(EVinfo.class).add(Restrictions.eq("owner", dDepartment))
//                                .add(Restrictions.eq("kind",kind)).setReadOnly(true);
//                        c.setFetchMode("owner",FetchMode.JOIN);
//                        List<EVinfo> evs = c.list();  //get evinfos by kind
//                        map.put(kind,evs);
//                    }
//                }
//            }
//        }else {
//
//        }
//        return map;
//    }
}
