package com.codedream.intf.DAO;

import com.codedream.intf.entity.CourseFinal;
import com.codedream.intf.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/4/2210:50
 */
public interface CourseFinalDAO  extends JpaRepository<CourseFinal,Integer> {
    CourseFinal findByIdIs(int id);
    List<CourseFinal> findAllByCourseNameLike(String s);
    List<CourseFinal> findAllByIdBetweenOrderByIdAsc(int start,int end);
}
