package com.codedream.intf.DAO;


import com.codedream.intf.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);
}
