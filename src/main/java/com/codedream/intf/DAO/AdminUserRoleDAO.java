package com.codedream.intf.DAO;


import com.codedream.intf.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    AdminUserRole findByUid(int uid);
    void deleteAllByUid(int uid);
}
