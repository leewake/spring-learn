package com.pangpang.controller.shiro;

import com.pangpang.util.ParamUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by leewake on 2017/8/11 0011.
 */

@Controller
public class HomeController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);

        Set<Map.Entry<String, String>> set = ParamUtil.getParams(request);

        String username = "admin", password = "123456";

        for (Map.Entry entry : set) {
            if (entry.getKey().equals("username")){
                if (entry.getValue() != null){
                    username = (String) entry.getValue();
                }else {
                    username = "admin";
                }
            }

            if (entry.getKey().equals("password")){
                if (entry.getValue() != null){
                    password = (String) entry.getValue();
                }else {
                    password = "123456";
                }
            }
        }

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(false);
            currentUser.login(token);

            return "/login";
        }

        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "403";
    }

}
