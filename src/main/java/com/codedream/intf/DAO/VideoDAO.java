package com.codedream.intf.DAO;


import com.codedream.intf.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VideoDAO extends JpaRepository<Video,Integer> {
    List<Video> getAllByDateNotNullOrderByDate();

    List<Video> findAllByNameLikeOrAbsLike(String s, String s1);

    List<Video> findAllByCidIs(int cid);


}
