package com.iwebirth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwebirth.controller.responsemodel.LoginStatus;
import com.iwebirth.util.StaticParam;

/**
 * 返回名为level的页面
 * **/
@Controller
public class ViewsController {

    @RequestMapping(value = "/{level}")
    public ModelAndView viewDispatcher(HttpSession session, ModelMap model, @PathVariable String level) {
        session.setMaxInactiveInterval(3600*12);
        LoginStatus status = (LoginStatus) session.getAttribute("login_status");
        ModelAndView mav = new ModelAndView();
        if (status != null && level != null) {
            model.put("username", status.getUsername());
            mav.setViewName(level);  //http://contextPath()/level
        } else {
            mav = new ModelAndView("redirect:/login");
        }
        return mav;
    }

}
