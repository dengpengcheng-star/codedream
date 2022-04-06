package com.codedream.controller;


import com.codedream.cool.result.Result;
import com.codedream.cool.result.ResultFactory;
import com.codedream.intf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LibraryController {
    @Autowired
    VideoService videoService;

    @GetMapping("/api/videos")
    public Result listBooks() {
        return ResultFactory.buildSuccessResult(videoService.getAllOrderByDate());
    }

//    @PostMapping("/api/admin/content/videos")
//    public Result addOrUpdateBooks(@RequestBody @Valid Video video) {
//        videoService.addOrUpdate(video);
//        return ResultFactory.buildSuccessResult("修改成功");
//    }

//    @PostMapping("/api/admin/content/videos/delete")


    @GetMapping("/api/search")
    public Result searchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult(videoService.getAllOrderByDate());
        } else {
            return ResultFactory.buildSuccessResult(videoService.Search(keywords));
        }
    }

//    @GetMapping("/api/categories/{cid}/books")
//    public Result listByCategory(@PathVariable("cid") int cid) {
//        if (0 != cid) {
//            return ResultFactory.buildSuccessResult(bookService.listByCategory(cid));
//        } else {
//            return ResultFactory.buildSuccessResult(bookService.list());
//        }
//    }

//    @PostMapping("/api/admin/content/books/covers")
//    public String coversUpload(MultipartFile file) {
//        String folder = "D:/workspace/img";
//        File imageFolder = new File(folder);
//        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
//                .substring(file.getOriginalFilename().length() - 4));
//        if (!f.getParentFile().exists())
//            f.getParentFile().mkdirs();
//        try {
//            file.transferTo(f);
//            String imgURL = "http://localhost:8443/api/file/" + f.getName();
//            return imgURL;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

}
