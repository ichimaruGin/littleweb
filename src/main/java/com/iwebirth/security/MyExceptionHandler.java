package com.iwebirth.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
		if(ex instanceof CustomException){
			mav.setViewName("custom_error");
			mav.addAllObjects(model);
		}
		return mav;
	}

}
