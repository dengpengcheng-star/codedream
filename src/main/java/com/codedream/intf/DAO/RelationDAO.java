package com.codedream.intf.DAO;

import com.codedream.intf.entity.CourseFinal;
import com.codedream.intf.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/4/2214:31
 */
public interface RelationDAO extends JpaRepository<Relation,Integer> {

    List<Relation> findAllByUserIdIsOrderByDate(int userId);

    Relation findByUserIdIsAndCourseIdIs(int userId,int courseId);

    @Override
    <S extends Relation> S saveAndFlush(S s);


}
