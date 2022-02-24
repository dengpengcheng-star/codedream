package com.codedream.cd.controller;


//import com.codedream.cd.controller.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/u")
public class LoginController extends  BaseController{

//    @Resource
//    private UserService userService;
//
//    //hello
//    @GetMapping("/login")
//    public String show(Model model , HttpServletRequest request, HttpSession session) throws Exception {
//        // 1 判断有没有登录
//        int userId = UserUtils.dealUser(DOMAIN, request,session);
//
//        String callback = request.getParameter("callback");
//
//        // 2 跳转到url
//        if(userId != 0){
//            return "redirect:" + callback;
//        }
//        model.addAttribute("callback",callback);
//        return "login";
//
//    }
//
//    @PostMapping("/login/verify")
//    @ResponseBody
//    public ResponseDO verify(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("username =:" + userName + " , password=:" + password);
//
//        if(StringUtils.isEmpty(userName)){
//            return new ResponseDO(false, "用户名weikong", null);
//        }
//        if (StringUtils.isEmpty(password)) {
//            return new ResponseDO(false, "密码不正确", null);
//        }
//
//        User user = userService.loadByName(userName);
//
//        if(user == null){
//            return new ResponseDO(false, "用户名不", null);
//        }
//
//        if (!userService.verify(user,password)){
//            return new ResponseDO(false, "密码不正确", null);
//        }
//
//        try {
//            if(MD5Signature.verify(password, user.getPassword(), KeyConstant.MD5_KEY)){
//                String token = user.getId() +  "_" + MD5Signature.sign("" + user.getId(), KeyConstant.MD5_KEY);
//                CookieUtil.addCookie("token", token, DOMAIN, response);
//                return new ResponseDO(true, SUCCESS, null);
//            }
//            //
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        return new ResponseDO(false, "用户名不正确", null);
//
//    }
//
//
//    @PostMapping("/login/signup")
//    @ResponseBody
//    public ResponseDO signup(String userName, String password, HttpServletRequest request) {
//        System.out.println("username =:" + userName + " , password=:" + password);
//
//        if(StringUtils.isEmpty(userName)){
//            return new ResponseDO(false, "用户名不正确", null);
//        }
//        if (StringUtils.isEmpty(password)) {
//            return new ResponseDO(false, "密码不正确", null);
//        }
//
//        User user = userService.loadByName(userName);
//
//        if(user != null){
//            return new ResponseDO(false, "用户名已存在", null);
//        }
//
//        int userId=userService.addUser(userName,password);
//        if (userId==-1){
//            return new ResponseDO(false, "创建用户失败", null);
//        }
//
//
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("userId", userId);
//        //map.put("userTime", Time.now());
//
//        return new ResponseDO(true, SUCCESS, map);
//
//    }
}
