package com.codedream.MLFunction;

import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlgorithmController {
    @Autowired
    private AlgorithmService algoritmService;
    @Autowired
    LectureService lectureService;

    @PostMapping("/api/getRecommend")
    @ResponseBody
    public Result getRecCourse(){
        return ResultFactory.buildSuccessResult(lectureService.getAll());
        //todo
//        return ResultFactory.buildSuccessResult(algoritmService.recGet(mlRequest));
    }
}
