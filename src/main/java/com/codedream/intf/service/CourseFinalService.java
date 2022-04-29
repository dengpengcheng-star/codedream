package com.codedream.intf.service;

import com.codedream.intf.DAO.CourseFinalDAO;
import com.codedream.intf.entity.CourseFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CourseFinalService {
    @Autowired
    CourseFinalDAO courseFinalDAO;

    public List<CourseFinal> getCourseFinalList(int[] ids){
        List<CourseFinal> list=new LinkedList<>();
        for(int i=0;i<ids.length;i++){
            list.add(courseFinalDAO.findByIdIs(ids[i]));
        }
        return list;
    }
    public List<CourseFinal> getAll(){
        return courseFinalDAO.findAll();
    }

    public List<CourseFinal> Search(String keywords) {
        return courseFinalDAO.findAllByCourseNameLike('%' + keywords + '%');
    }

    public List<CourseFinal> findByIdI(int lid) {

        return courseFinalDAO.findAllByIdBetweenOrderByIdAsc(lid*100-100,lid*100-1);
    }
}
