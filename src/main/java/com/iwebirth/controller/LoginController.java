package com.iwebirth.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iwebirth.controller.requestmodel.LoginInfo;
import com.iwebirth.controller.responsemodel.LoginResponse;
import com.iwebirth.controller.responsemodel.LoginStatus;
import com.iwebirth.db.service.UserService;
import com.iwebirth.security.CustomException;
import com.iwebirth.util.StaticParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	//baseURL/login
	@RequestMapping
	public
	ModelAndView index(HttpSession session){
		session.setMaxInactiveInterval(3600);
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	//@RequestMapping(value="/check",method=RequestMethod.POST) 
	/**
	 * method设置为POST时。直接在浏览器访问/login/check会报错 HTTP Status 405 - Request method 'GET' not supported
	 * **/
	@RequestMapping(value="/check",method=RequestMethod.POST)
	public
	@ResponseBody LoginResponse check(HttpSession session,LoginInfo loginInfo) throws Exception{
		LoginResponse loginResponse = new LoginResponse();		
		if(!loginInfo.isValid()){
			System.out.println("抛出CustomException@LoginController.loginResponse.check()");
			throw new CustomException("测试Spring异常捕获");
		}else{
			loginInfo.show();
			session.setMaxInactiveInterval(3600);
			//LoginStatus status = (LoginStatus)session.getAttribute("login_status");		
			if(loginInfo.getAuthcode().equals(session.getAttribute("auth_code"))){
				loginResponse = userService.checkUser(loginInfo);
				if(loginResponse.getResult().equals(StaticParam.LOGIN_RESPONSE_SUCCESS)){  //login success
					LoginStatus newStatus = new LoginStatus(loginResponse.getUsername(),loginResponse.getLevel(),true);
					session.setAttribute("login_status", newStatus);
				}
				return loginResponse;
			}else{				
				loginResponse.setUsername(loginInfo.getUsername());
				loginResponse.setResult(StaticParam.LOGIN_RESPONSE_FAIL_AUTHCODE);
			}
					
		}
		return loginResponse;
	}
	
	@RequestMapping(value="/logout")
	public 
	ModelAndView logout(HttpSession session){
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/authcode/{time}",method=RequestMethod.GET)
	public 
	void authcode(HttpServletRequest request,HttpServletResponse response,HttpSession session,@PathVariable Long time) throws Exception{
		
		// 验证码图片的宽度。         
	    int width = 120;         
	    // 验证码图片的高度。         
	    int height = 30;         
	    // 验证码字符个数         
	    int codeCount = 4;         
	    int x = 0;         
	    // 字体高度         
	    int fontHeight;         
	    int codeY;         
	    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',         
	            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        // 将配置的信息转换成数值                
        x = width / (codeCount + 1);         
        fontHeight = height - 2;         
        codeY = height - 4;  
        /***生成验证码***/
        BufferedImage buffImg = new BufferedImage(width, height,         
                BufferedImage.TYPE_INT_RGB);         
        Graphics2D g = buffImg.createGraphics();         
        // 创建一个随机数生成器类         
        Random random = new Random();         
        // 将图像填充为白色         
        g.setColor(Color.WHITE);         
        g.fillRect(0, 0, width, height);         
        // 创建字体，字体的大小应该根据图片的高度来定。         
        Font font = new Font("Fixedsys", Font.ITALIC, fontHeight);         
        // 设置字体。         
        g.setFont(font);         
        // 画边框。         
        g.setColor(Color.BLACK);         
        g.drawRect(0, 0, width - 1, height - 1);         
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。         
        g.setColor(Color.BLACK); 
        for (int i = 0; i < 10; i++) {         
            int x0 = random.nextInt(width);         
            int y0 = random.nextInt(height);         
            int xl = random.nextInt(12);         
            int yl = random.nextInt(12);         
            g.drawLine(x0, y0, x0 + xl, y0 + yl);         
        }         
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。         
        StringBuffer randomCode = new StringBuffer();         
        int red, green, blue;
        // 随机产生codeCount数字的验证码。         
        for (int i = 0; i < codeCount; i++) {         
            // 得到随机产生的验证码数字。         
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);         
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。         
            red = random.nextInt(255);         
            green = random.nextInt(255);         
            blue = random.nextInt(255);         
            // 用随机产生的颜色将验证码绘制到图像中。         
            g.setColor(new Color(red, green, blue));         
            g.drawString(strRand, (i + 1) * x, codeY);         
            // 将产生的四个随机数组合在一起。         
            randomCode.append(strRand);         
        }    
     // 将四位数字的验证码保存到Session中。              
        session.setAttribute("auth_code", randomCode.toString()); 
       // System.out.println("get an Authcode : "+randomCode.toString());
        // 禁止图像缓存。         
        response.setHeader("Pragma", "no-cache");         
        response.setHeader("Cache-Control", "no-cache");         
        response.setDateHeader("Expires", 0);         
        response.setContentType("image/jpeg");         
        // 将图像输出到Servlet输出流中。         
        OutputStream sos = response.getOutputStream();         
        ImageIO.write(buffImg, "jpeg", sos);         
        sos.close();       
	}
}
