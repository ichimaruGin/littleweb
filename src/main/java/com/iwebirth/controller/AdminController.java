package com.iwebirth.controller;

import com.iwebirth.db.model.User;
import com.iwebirth.db.service.common.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by YY_410 on 2015/3/26.
 */
@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {

    @Autowired
    CommonDao commonDao;
    /**
     * records  对应实体的类名
     * *
     */
    @RequestMapping(value = "/query/{records}", method = RequestMethod.GET)
    public @ResponseBody
    List getAllRecords(@PathVariable String records,HttpSession session) {
        Class clazz = null;
        if(records.equals("user"))
            clazz = User.class;
        else
            ;
        List list = commonDao.getAllObject(clazz);
        return list;
    }
}
