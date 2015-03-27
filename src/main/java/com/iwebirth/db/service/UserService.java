package com.iwebirth.db.service;

import com.iwebirth.db.service.common.CommonDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwebirth.controller.requestmodel.LoginInfo;
import com.iwebirth.controller.responsemodel.LoginResponse;
import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.User;
import com.iwebirth.util.StaticParam;

@Component
public class UserService {
    @Autowired
    SessionFactory sf;
    @Autowired
    CommonDao commonDao;

    /**
     * User login check service(contains username & password check)
     * Authcode check is in LoginController
     * *
     */
    public LoginResponse checkUser(LoginInfo loginInfo) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(loginInfo.getUsername());
        try {
            Session session = sf.getCurrentSession();
            Criteria c = session.createCriteria(User.class).add(Restrictions.eq("username", loginInfo.getUsername()));
            User dUser = (User) c.uniqueResult();
            if (dUser == null) {
                loginResponse.setResult(StaticParam.LOGIN_RESPONSE_FAIL_USER_ERROR); //has no such username
            } else {
                if (!dUser.getPassword().equals(loginInfo.getPassword()))
                    loginResponse.setResult(StaticParam.LOGIN_RESPONSE_FAIL_PASSWD_ERROR); //passwd error
                else {
                    loginResponse.setResult(StaticParam.LOGIN_RESPONSE_SUCCESS);
                    if (StaticParam.USER_LEVEL_DEPARTMENT.equals(dUser.getUserLevel())) {
                        //"department" 企业用户登陆
                        Department department = (Department) session.createCriteria(Department.class).add(Restrictions.eq("id", dUser.getDepartmentId())).uniqueResult();
                        if (department != null)
                            loginResponse.setLevel(department.getFunction());
                    } else {
                        //"admin" or "personal"  管理员或者家长
                        loginResponse.setLevel(dUser.getUserLevel());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginResponse;
    }


}
