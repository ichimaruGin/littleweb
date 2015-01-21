package com.iwebirth.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iwebirth.controller.responsemodel.Authcode;
import com.iwebirth.controller.responsemodel.LoginInfo;
import com.iwebirth.controller.responsemodel.LoginResult;
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
	ModelAndView index(HttpSession session,ModelMap model){
		session.setMaxInactiveInterval(3600);
		UserStatus status = (UserStatus)session.getAttribute("user_status");
		ModelAndView mav = new ModelAndView();
		if(status != null && status.getAlive()){			
			mav.setViewName("head");
			model.addAttribute("username","yuyang");
		}else{
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/check")
	public
	@ResponseBody LoginResult check(HttpSession session,HttpServletResponse response,LoginInfo loginInfo) throws IOException{
		session.setMaxInactiveInterval(3600);
		UserStatus status = (UserStatus)session.getAttribute("user_status");
		LoginResult loginResult = new LoginResult();
		if(status != null && status.getAlive()){	
			//todo 根据UserStatus 的level 来确定返回到特定页面

		}else{
			//首次登陆或者session失效
			loginInfo.show();		
			if(loginInfo.getAuthcode().equals((String)session.getAttribute("authcode"))){
				System.out.println("验证码通过");				
				loginResult.setUsername(loginInfo.getUsername());
				loginResult.setResult("SUCCESS");
				loginResult.setSuccess(true);
				UserStatus userStatus = new UserStatus(loginInfo.getUsername(),"admin",true);
				session.setAttribute("user_status", userStatus);
				return loginResult;
			}else{				
				loginResult.setUsername(loginInfo.getUsername());
				loginResult.setResult("FAIL_AUTHCODE");
				loginResult.setSuccess(true);
			}
		}		
		return loginResult;
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
	            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',         
	            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };          
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
        int red = 0, green = 0, blue = 0;         
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
        session.setAttribute("authcode", randomCode.toString()); 
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
