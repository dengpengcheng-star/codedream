package com.codedream.intf.DAO;

import com.codedream.intf.entity.AdminMenu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
     AdminMenu findByIdIs(int id);
//     List<AdminMenu> findAllByParentId(int parentId);
//     List<AdminMenu> findAllById(int parentId);
}

