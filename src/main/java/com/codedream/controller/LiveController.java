package com.codedream.controller;

import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.entity.ClassInfo;
import com.codedream.intf.entity.User;
import com.codedream.intf.live.SingleLiveMap;
import com.codedream.intf.service.ClassService;
import com.codedream.intf.service.LectureService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class LiveController {
    @Autowired
    ClassService classService;
    @Autowired
    LectureService lectureService;

    private SingleLiveMap singleLiveMap=SingleLiveMap.getInstance();

    @GetMapping("/api/live/{lid}/up")
    public Result updateLive(@PathVariable("lid") int lid) {
        Subject subject = SecurityUtils.getSubject();

        User userinfo = (User) subject.getPrincipal();
       if(singleLiveMap.updateLive(lid,userinfo.getId(),true)!=-1){
           return ResultFactory.buildSuccessResult(null);
       }else {
           return ResultFactory.buildFailResult("no such liveRoom");
       }
    }
    @GetMapping("/api/live/{lid}/remove")
    public Result remover(@PathVariable("lid") int lid) {
        Subject subject = SecurityUtils.getSubject();

        User userinfo = (User) subject.getPrincipal();
        if(singleLiveMap.updateLive(lid,userinfo.getId(),false)!=-1){
            return ResultFactory.buildSuccessResult(null);
        }else {
            return ResultFactory.buildFailResult("no such liveRoom");
        }
    }
    @GetMapping("/api/live/{lid}/getUpList")
    public Result getList(@PathVariable("lid") int lid) {
        Set<Integer> set=singleLiveMap.getOnlineUsers(lid);
        if(set==null){
            return ResultFactory.buildFailResult("no such liveRoom");
        }
        int cid=lectureService.findByIdIs(lid).getCid();
        List<ClassInfo> classInfoList=classService.getStudents(cid);
        for (ClassInfo student:classInfoList
             ) {
            if(set.contains(student.getId())){
                student.setUp("true");
            }else {
                student.setUp("false");
            }
        }return ResultFactory.buildSuccessResult(classInfoList);

    }
}
