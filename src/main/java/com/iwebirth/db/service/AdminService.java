package com.iwebirth.db.service;

import com.iwebirth.db.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YY_410 on 2015/3/27.
 */
@Service
public class AdminService {
    @Autowired
    SessionFactory sessionFactory;

    /**
     * User update
     * 对username，password，userLevel，departmentId，isValid进行更新
     * **/
    public int updateUser(User user){
        Session session = sessionFactory.getCurrentSession();
        int res = CRUDEvent.UPDATE_SUCCESS.getValue();
        try{
            Integer id = user.getId();
            User dUser = (User)session.get(User.class,id);
            if(dUser != null){
                dUser.setUsername(user.getUsername());
                dUser.setPassword(user.getPassword());
                dUser.setUserLevel(user.getUserLevel());
                dUser.setIsValid(user.getIsValid());
                dUser.setDepartmentId(user.getDepartmentId());
            }else{
                res = CRUDEvent.UPDATE_FAIL.getValue();
            }
        }catch(Exception e){
            e.printStackTrace();
            res = CRUDEvent.UPDATE_EXCEPTION.getValue();
        }
        return res;
    }
}
