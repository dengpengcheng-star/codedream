package com.codedream.intf.service;

import com.codedream.intf.DAO.RelationDAO;
import com.codedream.intf.entity.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/4/2214:35
 */
@Service
public class RelationService {
    @Autowired
    RelationDAO relationDAO;
    public List<Relation> getRelations(int uid){
        return relationDAO.findAllByUserIdIsOrderByDate(uid);
    }

    public boolean isRelationExists(int uid,int cid){
        return !(relationDAO.findByUserIdIsAndCourseIdIs(uid,cid)==null);
    }
    public Relation getRelation(int uid,int cid){
        return relationDAO.findByUserIdIsAndCourseIdIs(uid,cid);
    }

    public Relation createRelation(int uid, int cid) {
        Relation relation=new Relation();
        relation.setCompletion(50);
        relation.setCourseId(cid);
        relation.setUserId(uid);
        relation.setDate(new Date());
        return relationDAO.saveAndFlush(relation);

    }

    public Relation increase(int uid, int cid) {
        Relation relation=relationDAO.findByUserIdIsAndCourseIdIs(uid,cid);
        if(relation.getCompletion()+50>100){
            relation.setCompletion(100);
        }else {
            relation.setCompletion(relation.getCompletion()+50);
        }
        return relationDAO.saveAndFlush(relation);
    }
}
