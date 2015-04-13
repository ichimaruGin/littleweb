package com.iwebirth.controller;

import com.iwebirth.controller.responsemodel.AjaxResult;
import com.iwebirth.controller.responsemodel.LoginStatus;
import com.iwebirth.db.model.Department;
import com.iwebirth.db.model.User;
import com.iwebirth.db.model.Vehicle;
import com.iwebirth.db.service.AdminService;
import com.iwebirth.db.service.CRUDEvent;
import com.iwebirth.db.service.common.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.management.modelmbean.ModelMBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YY_410 on 2015/3/26.
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
    private static final int vehicle_id = 2;
    /**
     * obj_id 这个值代表需要修改的对象 比如User 为 0, department_id 为 1
     */
    @RequestMapping(value = "/query/{obj_id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List getAllRecords(@PathVariable int obj_id, HttpSession session) {
        LoginStatus status = (LoginStatus) session.getAttribute("login_status");
        if (status == null || !status.getAlive())
            return null;
        Class clazz = null;
        switch(obj_id){
            case user_id: clazz = User.class;break;
            case department_id: clazz = Department.class;break;
            case vehicle_id: clazz = Vehicle.class;break;
        }
        List list = commonDao.getAllObject(clazz);
        return list;
    }

    /**
     * *
     */
    @RequestMapping(value = {"/update/{obj_id}"}, method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxResult updateRecord(@PathVariable int obj_id, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        LoginStatus status = (LoginStatus) session.getAttribute("login_status");
        if (status == null || !status.getAlive())
            return null;
        request.setCharacterEncoding("utf-8");
        AjaxResult ajaxResult = new AjaxResult(false);
        try {
            switch (obj_id) {
                //User更新请求
                case user_id:
                    User user = new User();
                    user.setId(Integer.parseInt(request.getParameter("id")));
                    user.setUsername(request.getParameter("username"));
                    user.setPassword(request.getParameter("password"));
                    user.setUserLevel(request.getParameter("userLevel"));
                    user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
                    user.setIsValid(Boolean.parseBoolean(request.getParameter("isValid")));
                    ajaxResult.setResult(CRUDEvent.getNameByValue(adminService.updateObject(user)));
                    break;
                case department_id:
                    Department depart = new Department();
                    depart.setId(Integer.parseInt(request.getParameter("id")));
                    depart.setName(request.getParameter("name"));
                    depart.setLocation(request.getParameter("location"));
                    depart.setFunction(request.getParameter("function"));
                    depart.setLatitude(request.getParameter("latitude"));
                    depart.setLongitude(request.getParameter("longitude"));
                    ajaxResult.setResult(CRUDEvent.getNameByValue(adminService.updateObject(depart)));
                    break;
                case vehicle_id:
                    Vehicle vehicle = new Vehicle();
                    vehicle.setId(Integer.parseInt(request.getParameter("id")));
                    vehicle.setTerminalId(request.getParameter("terminalId"));
                    vehicle.setType(request.getParameter("type"));
                    vehicle.setTerminalLicense(request.getParameter("terminalLicense"));
                    vehicle.setCurrentStatus(request.getParameter("currentStatus"));
                    vehicle.setBelongId(Integer.parseInt(request.getParameter("belongId")));
                    vehicle.setOriginId(Integer.parseInt(request.getParameter("originId")));
                    vehicle.setRecentRentId(Integer.parseInt(request.getParameter("recentRentId")));
                    vehicle.setRecentSellId(Integer.parseInt(request.getParameter("recentSellId")));
                    ajaxResult.setResult(CRUDEvent.getNameByValue(adminService.updateObject(vehicle)));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxResult;
    }

    @RequestMapping(value = {"/delete/{obj_id}"}, method = RequestMethod.POST)
    public
    @ResponseBody
    AjaxResult deleteRecord(@PathVariable int obj_id, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        LoginStatus status = (LoginStatus) session.getAttribute("login_status");
        if (status == null || !status.getAlive())
            return null;
        request.setCharacterEncoding("utf-8");
        AjaxResult ajaxResult = new AjaxResult(false);
        try {
            switch (obj_id) {
                case user_id:
                    int id = Integer.parseInt(request.getParameter("id"));
                    String res = CRUDEvent.getNameByValue(adminService.deleteUserById(id));
                    ajaxResult.setResult(res);
                    System.out.println(res);
                    break;
                case department_id:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxResult;
    }

    //获得单个对象
    @RequestMapping(value = {"/others/single/{obj_id}/{id}"}, method = RequestMethod.GET)
    public
    @ResponseBody
    Object getDepartmentName(@PathVariable Integer id, @PathVariable Integer obj_id, HttpSession session) {
        LoginStatus status = (LoginStatus) session.getAttribute("login_status");
        if (status == null || !status.getAlive())
            return null;
        else {
            Class clazz = null;
            switch (obj_id){
                case 0: clazz = User.class;break;
                case 1: clazz = Department.class;break;
            }
            return commonDao.getSingleObjectById(clazz, id);
        }
    }

}
