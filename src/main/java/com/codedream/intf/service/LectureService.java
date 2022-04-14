package com.codedream.intf.service;

import com.codedream.intf.DAO.LectureDAO;
import com.codedream.intf.entity.Lecture;
import com.codedream.intf.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {
    @Autowired
    LectureDAO lectureDAO;
    public Object listByCategory(int cid) {
       return lectureDAO.findAllByCidIs(cid);
    }
    public List<Lecture> getAll(){
        return lectureDAO.findAll();
    }
    public List<Lecture> Search(String keywords) {
        return lectureDAO.findAllByNameLikeOrAbsLike('%' + keywords + '%', '%' + keywords + '%');
    }
    public Lecture findByIdIs(int id){
        return lectureDAO.findByIdIs(id);
    }
}
