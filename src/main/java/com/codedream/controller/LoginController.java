package com.codedream.controller;




import com.codedream.intf.entity.User;
import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.service.UserService;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;




@Controller
public class LoginController {

    @Autowired
    UserService userService;
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {

        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Subject subject = SecurityUtils.getSubject();
        logger.info("middle");
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            User user = userService.findByUsername(username);
//            if (!user.isEnabled()) {
//                return ResultFactory.buildFailResult("该用户已被禁用");
//            }
            return ResultFactory.buildSuccessResult(username);
        } catch (IncorrectCredentialsException e) {
            logger.info("error----");
            return ResultFactory.buildFailResult("password is error");
        } catch (UnknownAccountException e) {
            logger.info("error----");
            return ResultFactory.buildFailResult("Account is not exists");
        }
    }
    @PostMapping("/api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("username and password can not be empty");
            case 1:
                return ResultFactory.buildSuccessResult("success");
            case 2:
                return ResultFactory.buildFailResult("The user is already exits");
        }
        return ResultFactory.buildFailResult("Error");
    }

    @GetMapping("/api/logout")
    @ResponseBody
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("成功登出");
    }

    //访问每个页面前都向后端发送一个请求，目的是经由拦截器验证服务器端的登录状态,防止自建请求
    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }
}
