package com.codedream.intf.DAO;

import com.codedream.intf.entity.AdminRole;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
}
