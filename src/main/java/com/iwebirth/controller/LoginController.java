package com.iwebirth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iwebirth.controller.responsemodel.UserStatus;
import com.iwebirth.db.model.DefaultUser;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public 
	@ResponseBody DefaultUser getSingleUser(HttpServletRequest request,HttpServletResponse response){
		DefaultUser defaultUser = new DefaultUser("test", request.getRequestURI());
		return defaultUser;
	}
	
	@RequestMapping
	public
	ModelAndView index(HttpSession session){
		session.setMaxInactiveInterval(3600);
		UserStatus status = (UserStatus)session.getAttribute("user_status");
		ModelAndView mav = new ModelAndView();
		if(status != null && status.getAlive()){			
			mav.setViewName("");
		}else{
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/check")
	public
	ModelAndView check(HttpSession session){
		session.setMaxInactiveInterval(3600);
		UserStatus status = (UserStatus)session.getAttribute("user_status");
		ModelAndView mav = new ModelAndView();
		if(status != null && status.getAlive()){			
			mav.setViewName("");
		}else{
			mav.setViewName("index");
		}
		return mav;
	}
}
