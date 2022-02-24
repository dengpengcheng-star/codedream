package com.codedream.cd.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HomeController extends  BaseController{

//    @Resource
//    private UserTimeService userTimeService;
//
//    //hello
//    @GetMapping("/index")
//    public String showHome(Model model , HttpServletRequest request){
//        String hello = "hello";
//
//
//        model.addAttribute("name1", hello);
//        model.addAttribute("name2", "allen");
//
//        return "hello";
//
//    }
//
//    @GetMapping("/init")
//    @ResponseBody
//    public ResponseDO init(String name, HttpServletRequest request){
//        System.out.println("init hello , name =" + name);
//
//        UserTime time=  userTimeService.loadByName(name);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("userId", 12);
//        map.put("userTime",  time);
//
//        return new ResponseDO(true, SUCCESS, map);
//    }
//
//    @GetMapping("/getListByUsrId")
//    @ResponseBody
//    public ResponseDO getListByUsrId(int userId, HttpServletRequest request){
//        List<UserTime> list=userTimeService.loadListByUserId(userId);
//
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("userList",  list);
//
//        return new ResponseDO(true, SUCCESS, map);
//    }
}
