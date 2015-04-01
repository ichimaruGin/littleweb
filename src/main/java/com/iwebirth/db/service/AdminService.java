package com.iwebirth.db.service;

import com.iwebirth.db.model.User;
import com.iwebirth.db.service.common.CommonDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YY_410 on 2015/3/27.
 */
@Service
public class AdminService {
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CommonDao commonDao;
    /**
     * User update & add
     * 对password，userLevel，departmentId，isValid进行更新
     * 或者对新的user进行save
     * *
     */
    public int updateUser(User user) {
        //Session session = sessionFactory.getCurrentSession();
        int res = CRUDEvent.UPDATE_SUCCESS.getValue();
        try {
            Integer id = user.getId();
            if(id == 0){
                //save
                res = commonDao.insertSingleObject(user);
            }else{
                //update
                User dUser = (User)commonDao.getSingleObjectById(User.class,id);
                dUser.setUsername(user.getUsername());
                dUser.setPassword(user.getPassword());
                dUser.setUserLevel(user.getUserLevel());
                dUser.setIsValid(user.getIsValid());
                dUser.setDepartmentId(user.getDepartmentId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = CRUDEvent.UPDATE_EXCEPTION.getValue();
        }
        return res;
    }

    /**
     * 并不是删除记录，而是将记录的账号有效性字段设为false
     * 其实做了一个update操作
     * *
     */
    public int deleteUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        int res = CRUDEvent.UPDATE_SUCCESS.getValue();
        try {
            User dUser = (User) session.get(User.class, id);
            if (dUser != null)
                dUser.setIsValid(false);
            else
                res = CRUDEvent.UPDATE_FAIL.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            CRUDEvent.UPDATE_EXCEPTION.getValue();
        }
        return res;
    }
}
