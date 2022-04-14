package com.codedream.intf.DAO;

import com.codedream.intf.entity.AdminUserRole;
import com.codedream.intf.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author DengPengcheng
 * @create 2022/4/1414:07
 */
public interface ClassInfoDAO extends JpaRepository<ClassInfo,Integer> {
    List<ClassInfo> findByCidIs(int cid);
}
