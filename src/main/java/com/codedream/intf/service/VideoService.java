package com.codedream.intf.service;

import com.codedream.intf.DAO.VideoDAO;
import com.codedream.intf.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    VideoDAO videoDAO;

    public List<Video> getAllOrderByDate(){
        return videoDAO.getAllByDateNotNullOrderByDate();
    }
    public List<Video> Search(String keywords) {
        return videoDAO.findAllByNameLikeOrAbsLike('%' + keywords + '%', '%' + keywords + '%');
    }
}