package com.codedream.intf.DAO;

import com.codedream.intf.entity.AdminUserRole;
import com.codedream.intf.entity.Lecture;
import com.codedream.intf.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/4/1313:42
 */
public interface LectureDAO extends JpaRepository<Lecture,Integer> {
    List<Lecture> findAllByCidIs(int cid);

    List<Lecture> findAllByNameLikeOrAbsLike(String s, String s1);
    List<Lecture> findAll();
    Lecture findByIdIs(int id);
}
