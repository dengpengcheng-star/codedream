package com.codedream.cd.controller;



import com.codedream.cd.intf.DO.Result;
import com.codedream.cd.intf.entity.User;
import com.codedream.cd.intf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping("/hello")
public class HomeController extends  BaseController{
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/index/show")
    @ResponseBody
    public Result showPictures(@RequestBody User requestUser) {

        String staticPath=ClassUtils.getDefaultClassLoader().getResource("pictures").getPath();

        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }

}
