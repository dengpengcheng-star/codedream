package com.codedream.cd.controller;




import com.codedream.cd.intf.entity.User;
import com.codedream.cd.intf.result.Result;
import com.codedream.cd.intf.result.ResultFactory;
import com.codedream.cd.intf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;



@Controller
public class LoginController extends  BaseController{

    @Autowired
    UserService userService;

//    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {

        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return ResultFactory.buildFailResult("未登录");
        } else {
            return ResultFactory.buildSuccessResult("");
        }
    }
    @PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("用户名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @GetMapping("/api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("成功登出");
    }

    @GetMapping("/api/authentication")
    public String authentication() {
        return "身份认证成功";
    }
}
