package com.codedream.controller;

import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.service.AdminMenuService;
import com.codedream.intf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;
    @Autowired
    UserService userService;

    @GetMapping("/api/menu")
    public Result menu() {

        return ResultFactory.buildSuccessResult(

                adminMenuService.getMenusByCurrentUser());
    }

//    @GetMapping("/api/admin/role/menu")
//    public Result listAllMenus() {
//
//
//        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByRoleId(1));
//    }
}
