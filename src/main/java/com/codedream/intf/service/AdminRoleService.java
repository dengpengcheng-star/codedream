package com.codedream.intf.service;

import com.codedream.intf.DAO.AdminRoleDAO;

import com.codedream.intf.entity.AdminMenu;
//import com.codedream.intf.entity.AdminPermission;
import com.codedream.intf.entity.AdminRole;
import com.codedream.intf.entity.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
//    @Autowired
//    AdminPermissionService adminPermissionService;
//    @Autowired
//    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminMenuService adminMenuService;

//    public List<AdminRole> listWithPermsAndMenus() {
//        List<AdminRole> roles = adminRoleDAO.findAll();
//        List<AdminPermission> perms;
//        List<AdminMenu> menus;
//        for (AdminRole role : roles) {
//            perms = adminPermissionService.listPermsByRoleId(role.getId());
//            menus = adminMenuService.getMenusByRoleId(role.getId());
//            role.setPerms(perms);
//            role.setMenus(menus);
//        }
//        return roles;
//    }

    public List<AdminRole> findAll() {
        return adminRoleDAO.findAll();
    }


    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public AdminRole listRolesByUser(String username) {
        int uid = userService.findByUsername(username).getId();
        AdminUserRole rids = adminUserRoleService.getByUid(uid);
        return adminRoleDAO.findById(rids.getRid());
    }

    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return adminRoleDAO.save(roleInDB);
    }

//    public void editRole(@RequestBody AdminRole role) {
//        adminRoleDAO.save(role);
//        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
//    }
}
