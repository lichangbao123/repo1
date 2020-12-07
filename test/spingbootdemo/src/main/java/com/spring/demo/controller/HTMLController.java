package com.spring.demo.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.demo.domain.SysPermission;
import com.spring.demo.domain.UserInfo;

@Controller
public class HTMLController {
	@RequestMapping({"/","/index"})
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
//        List<SysPermission> menus = getMenusByUser(user);
//        String username = user.getName();
//        
//        
//        request.setAttribute("username",username);
//        request.setAttribute("menus",menus);
        return "thymeleaf/index";
    }
	@RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        
    	System.out.println("---login start");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println(" ---账号不存在 ");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println(" ---密码不正确 ");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println(" ---验证码错误 ");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "thymeleaf/login";
    }
}
