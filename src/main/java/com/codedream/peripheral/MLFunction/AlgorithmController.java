package com.codedream.peripheral.MLFunction;

import com.alibaba.fastjson.JSONObject;
import com.codedream.controller.LoginController;
import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.DAO.UserDAO;
import com.codedream.intf.entity.User;
import com.codedream.intf.service.CourseFinalService;
import com.codedream.intf.service.LectureService;
import com.codedream.intf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlgorithmController {
    @Autowired
    private AlgorithmService algoritmService;
    @Autowired
    private CourseFinalService courseFinalService;
    @Autowired
    LectureService lectureService;
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @PostMapping("/api/getRecommend")
    @ResponseBody
    public Result getRecCourse(){
//        return ResultFactory.buildSuccessResult(lectureService.getAll());
        Object principal= SecurityUtils.getSubject().getPrincipal();

        String username = ((User)principal).getUsername();


        User user = userService.findByUsername(username);
        logger.info(String.valueOf(user.getId()));
        int[] ids=algoritmService.recGet(user.getId());

        return ResultFactory.buildSuccessResult(courseFinalService.getCourseFinalList(ids));
    }
}
