package com.codedream.controller;

import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.entity.ClassInfo;
import com.codedream.intf.entity.User;
import com.codedream.intf.live.SingleLiveMap;
import com.codedream.intf.live.TencentYunVedioUtil;
import com.codedream.intf.service.ClassService;
import com.codedream.intf.service.LectureService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    /**

     * 生成推流全路径地址

     *      * @param pushUrl 推流地址 头部

     *      * @param stream_id 直播码  调用该方法genLiveCode() 生成

     *      * @param push_key 推流鉴权

     *      * @return

     *

     */

    @PostMapping("createPushUrl")

    public static String createPushUrl() {

        //过期时间 16进制的unix时间戳

        String txTime = TencentYunVedioUtil.to16Hex(TencentYunVedioUtil.addOneDay());

        // 填写自己的streamName

        String streamName="xxxxx";

        StringBuffer sb = new StringBuffer();

        String pushUrlHead= TencentYunVedioUtil.pushUrl;

        // String stream_id = TencentYunVedioUtil.bizid();

        sb.append(pushUrlHead).append("?").append("txSecret=")

                .append(TencentYunVedioUtil.genSign(TencentYunVedioUtil.push_key, streamName,txTime)).append("&").append("txTime=")

                .append(txTime);

        return sb.toString();

    }

    /**

     *      * 生成拉流全路径地址

     *      * @param playUrlHead  拉流的头地址

     *      * @param stream_id  直播码  调用该方法genLiveCode() 生成

     *      * @param play_key  拉流鉴权

     *      * @param end 如 .flv结尾

     *      * @return

     */

    @PostMapping("createPlayUrl")

    public static String createPlayUrl() {

        //过期时间 16进制的unix时间戳

        String txTime = TencentYunVedioUtil.to16Hex(TencentYunVedioUtil.addOneDay());

        StringBuffer sb = new StringBuffer();

        //String stream_id = TencentYunVedioUtil.bizid();

        sb.append(TencentYunVedioUtil.playUrl_flv).append("?").append("txSecret=").append(TencentYunVedioUtil.pushSign(TencentYunVedioUtil.play_key, txTime))

                .append("&")

                .append("txTime=")

                .append(txTime);

        return sb.toString();

    }
}
