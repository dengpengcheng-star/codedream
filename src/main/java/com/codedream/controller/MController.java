package com.codedream.controller;

import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.entity.ClassInfo;
import com.codedream.intf.entity.User;
import com.codedream.intf.service.RelationService;
import com.codedream.intf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author DengPengcheng
 * @create 2022/4/2214:37
 */
@RestController
public class MController {
    @Autowired
    RelationService relationService;
    @Autowired
    UserService userService;
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/api/m/getVideoH")
    public Result getVideoH() {
        Object principal= SecurityUtils.getSubject().getPrincipal();

        String username = ((User)principal).getUsername();


        User user = userService.findByUsername(username);
        return ResultFactory.buildSuccessResult(
                relationService.getRelations(user.getId()));
    }
    @GetMapping("/api/playVideo/{cid}/on")
    public Result getList(@PathVariable("cid") int cid) {

        Object principal= SecurityUtils.getSubject().getPrincipal();

        String username = ((User)principal).getUsername();

        logger.info(String.valueOf(cid));
        User user = userService.findByUsername(username);
        int uid=user.getId();
        logger.info(String.valueOf(uid));
        if(relationService.isRelationExists(uid,cid)){

            logger.info(relationService.increase(uid,cid).toString());
        }else {logger.info( relationService.createRelation(uid,cid).toString());
//            relationService.increase(uid,cid);

        }

        return ResultFactory.buildSuccessResult("");


    }
    @GetMapping("/api/getUserInfo")
    public Result getUserInfo() {


        return ResultFactory.buildSuccessResult(

                userService.getInfo());
    }
    @PostMapping("/api/changeUserInfo")
    public Result changeUserInfo(@RequestBody HashMap<String, String> map) {
        User user=new User();
        user.setEmail(map.get("email"));

        user.setUsername(map.get("username"));
        user.setName(map.get("name"));
        user.setPhone(map.get("phone"));
        if(map.get("password2").equals("")){
            user.setPassword(map.get("password"));
        }else {
            user.setPassword(map.get("password2"));
        }
        int result=userService.changeInfo(user,map.get("password"));

        if(result==0){
            return ResultFactory.buildSuccessResult(
""
            );

        }else if(result==1){
           return ResultFactory.buildFailResult("password is wrong!");
        }
        return ResultFactory.buildFailResult("Error!");
    }
}
