package com.codedream.intf.service;


import com.codedream.controller.LoginController;
import com.codedream.intf.DAO.AdminMenuDAO;
import com.codedream.intf.entity.AdminMenu;
import com.codedream.intf.entity.AdminRoleMenu;
import com.codedream.intf.entity.AdminUserRole;
import com.codedream.intf.entity.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    public List<AdminMenu> getAllByParentId(int parentId) {
//        return adminMenuDAO.findAllByParentId(parentId);
//    }

    public List<AdminMenu> getMenusByCurrentUser() {

        // Get current user in DB.
        Object principal=SecurityUtils.getSubject().getPrincipal();
        logger.info("uid....."+principal.toString());
        String username = ((User)principal).getUsername();

        User user = userService.findByUsername(username);

        logger.info("uid....."+String.valueOf(user.getId()));

        // Get roles' ids of current user.
        AdminUserRole userRole = adminUserRoleService.getByUid(user.getId());
        int rid=userRole.getRid();

        logger.info("rid....."+String.valueOf(rid));

        // Get menu items of these roles.
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());

        logger.info("mid....."+String.valueOf(menuIds));
        List<AdminMenu> menus=new LinkedList<>();
        for (Integer mid:menuIds
             ) {
            menus.add(adminMenuDAO.findByIdIs(mid));
        }
        logger.info("mid....."+String.valueOf(menus));
//        = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the menu.
        //handleMenus(menus);
        return menus;
    }

//    public List<AdminMenu> getMenusByRoleId(int rid) {
//        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
//                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
//        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);
//
////        handleMenus(menus);
//        return menus;
//    }

//    /**
//     * Adjust the Structure of the menu.
//     *
//     * @param menus Menu items list without structure
//     */
//    public void handleMenus(List<AdminMenu> menus) {
//        menus.forEach(m -> {
//            List<AdminMenu> children = getAllByParentId(m.getId());
//            m.setChildren(children);
//        });
//
//        menus.removeIf(m -> m.getParentId() != 0);
//    }
}
