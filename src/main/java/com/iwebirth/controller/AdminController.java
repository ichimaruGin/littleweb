package com.iwebirth.controller;

import com.iwebirth.controller.responsemodel.AjaxResult;
import com.iwebirth.controller.responsemodel.LoginStatus;
import com.iwebirth.db.model.User;
import com.iwebirth.db.service.AdminService;
import com.iwebirth.db.service.CRUDEvent;
import com.iwebirth.db.service.common.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.modelmbean.ModelMBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by YY_410 on 2015/3/26.
 *
 */
@Controller
@RequestMapping(value = {"/admin"})
public class AdminController {

    @Autowired
    CommonDao commonDao;

    @Autowired
    AdminService adminService;

    private static final int user_id = 0;
    private static final int department_id = 1;
    /**
     *obj_id 这个值代表需要修改的对象 比如User 为 0, department_id 为 1
     */
    @RequestMapping(value = "/query/{obj_id}", method = RequestMethod.GET)
    public @ResponseBody
    List getAllRecords(@PathVariable int obj_id,HttpSession session) {
        LoginStatus status = (LoginStatus)session.getAttribute("login_status");
        if(status == null || !status.getAlive())
            return null;
        Class clazz = null;
        if(obj_id == user_id)
            clazz = User.class;
        else
            ;
        List list = commonDao.getAllObject(clazz);
        return list;
    }

    /**
     *
     * **/
    @RequestMapping(value={"/update/{obj_id}"}, method = RequestMethod.POST)
    public @ResponseBody
    AjaxResult updateRecord(@PathVariable int obj_id, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        LoginStatus status = (LoginStatus)session.getAttribute("login_status");
        if(status == null || !status.getAlive())
            return null;
        request.setCharacterEncoding("utf-8");
        AjaxResult ajaxResult = new AjaxResult(false);
        try{
            switch(obj_id){
                //User更新请求
                case user_id:
                    User user = new User();
                    user.setId(Integer.parseInt(request.getParameter("id")));
                    user.setUsername(request.getParameter("username"));
                    user.setPassword(request.getParameter("password"));
                    user.setUserLevel(request.getParameter("userLevel"));
                    user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
                    user.setIsValid(Boolean.parseBoolean(request.getParameter("isValid")));
                    String res = CRUDEvent.getNameByValue(adminService.updateUser(user));
                    ajaxResult.setResult(res);
                    System.out.println(res);
                    break;
                case department_id:
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ajaxResult;
    }


    @RequestMapping(value={"/delete/{obj_id}"},method = RequestMethod.POST)
    public @ResponseBody
    AjaxResult deleteRecord(@PathVariable int obj_id, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        LoginStatus status = (LoginStatus)session.getAttribute("login_status");
        if(status == null || !status.getAlive())
            return null;
        request.setCharacterEncoding("utf-8");
        AjaxResult ajaxResult = new AjaxResult(false);
        try{
            switch(obj_id){
                case user_id:
                    int id = Integer.parseInt(request.getParameter("id"));
                    String res = CRUDEvent.getNameByValue(adminService.deleteUserById(id));
                    ajaxResult.setResult(res);
                    System.out.println(res);
                    break;
                case department_id:
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ajaxResult;
    }
 }
