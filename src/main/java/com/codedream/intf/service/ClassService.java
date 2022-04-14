package com.codedream.intf.service;

import com.codedream.intf.DAO.ClassInfoDAO;
import com.codedream.intf.entity.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/4/1414:10
 */
@Service
public class ClassService {
    @Autowired
    ClassInfoDAO classInfoDAO;

    public List<ClassInfo> getStudents(int cid){
        return classInfoDAO.findByCidIs(cid);
    }
}
