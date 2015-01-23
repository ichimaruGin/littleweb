package com.iwebirth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwebirth.controller.responsemodel.LoginStatus;

@Controller
public class ViewsController {
	@RequestMapping(value="/{level}")
	public
	ModelAndView dispatcher(HttpSession session,ModelMap model,@PathVariable String level){
		session.setMaxInactiveInterval(3600);
		System.out.println("level:"+level);
		LoginStatus status = (LoginStatus)session.getAttribute("login_status");
		ModelAndView mav = new ModelAndView();
		if(status != null){	
				if(level.equals(LoginStatus.ADMIN)){
					mav.setViewName("admin");
					model.put("username", status.getUsername());
				}else if(level.equals(LoginStatus.EV_USER)){
					mav.setViewName("ev_user");
					model.put("username", status.getUsername());
				}else if(level.equals(LoginStatus.EV_SELLER)){
					mav.setViewName("ev_seller");
					model.put("username", status.getUsername());
				}			
		}else{
			mav = new ModelAndView("redirect:/login");
		}
		return mav;
	}
}
