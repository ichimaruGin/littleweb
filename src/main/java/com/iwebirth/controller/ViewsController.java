package com.iwebirth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwebirth.controller.responsemodel.LoginStatus;
import com.iwebirth.util.StaticParam;

@Controller
public class ViewsController {
	@RequestMapping(value="/{level}")
	public
	ModelAndView dispatcher(HttpSession session,ModelMap model,@PathVariable String level){
		session.setMaxInactiveInterval(3600);
		LoginStatus status = (LoginStatus)session.getAttribute("login_status");
		ModelAndView mav = new ModelAndView();
		if(status != null){	
				model.put("username", status.getUsername());
				if(level.equals(StaticParam.USER_LEVEL_ADMIN)){
					mav.setViewName("admin");
				}else if(level.equals(StaticParam.USER_LEVEL_PARENT)){
					mav.setViewName("parent");
				}else if(level.equals(StaticParam.DEPARTMENT_STATUS_EV_BUYER)){
					mav.setViewName("ev_user");
				}else if(level.equals(StaticParam.DEPARTMENT_STATUS_EV_SELLER)){
					mav.setViewName("ev_seller");
				}			
		}else{
			mav = new ModelAndView("redirect:/login");
		}
		return mav;
	}
}
