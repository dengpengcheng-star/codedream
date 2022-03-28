package com.codedream.cd.intf.DAO;


import com.codedream.cd.intf.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/3/289:58
 */
public interface VideoDAO extends JpaRepository<Video,Integer> {
    List<Video> getAllByDateNotNullOrderByDate();

    List<Video> findAllByNameLikeOrAbsLike(String s, String s1);
}
