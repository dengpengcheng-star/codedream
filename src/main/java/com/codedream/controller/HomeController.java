package com.codedream.controller;




import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.entity.User;
import com.codedream.intf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import com.codedream.cool.result.Result;
@Controller
@RequestMapping("/hello")
public class HomeController extends  BaseController{
    @Autowired
    UserService userService;

//    @CrossOrigin
//    @PostMapping(value = "/api/index/show")
//    @ResponseBody
//    public Result showPictures(@RequestBody User requestUser) {
//
//        String staticPath=ClassUtils.getDefaultClassLoader().getResource("pictures").getPath();
//
//        String username = requestUser.getUsername();
//        username = HtmlUtils.htmlEscape(username);
//
//
//    }

}
