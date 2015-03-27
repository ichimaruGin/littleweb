package com.iwebirth.db.service.common;

import com.iwebirth.db.model.User;
import com.iwebirth.db.service.CRUDEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YY_410 on 2015/3/23.
 */

@Service
public class CommonDao {

    @Autowired
    SessionFactory sessionFactory;


    /**
     * @param object 插入的对象
     *               *
     */
    public int insertSingleObject(Object object) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            return (Integer) (session.save(object)) > 0 ? CRUDEvent.INSERT_SUCCESS.getValue() : CRUDEvent.INSERT_FAIL.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return CRUDEvent.INSERT_EXCEPTION.getValue();
        }

    }

    /**
     * @param c  Object.getClass()
     * @param id Object's id
     */
    public Object getSingleObjectById(Class c, int id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            Object o = session.get(c, id);
            return o != null ? o : null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("get异常");
        }
        return null;
    }

    /**
     * 获得某个类所有记录
     **/
    public List getAllObject(Class c) {
        List resList = new ArrayList();
        if(c == null)
            return resList;
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            resList = session.createCriteria(c)
                    .setReadOnly(true)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }

}
