//package com.codedream.intf.service;
//
//
//import com.codedream.intf.DAO.AdminRolePermissionDAO;
//import com.codedream.intf.entity.AdminPermission;
//import com.codedream.intf.entity.AdminRolePermission;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class AdminRolePermissionService {
//    @Autowired
//    AdminRolePermissionDAO adminRolePermissionDAO;
//
//    List<AdminRolePermission> findAllByRid(int rid) {
//        return adminRolePermissionDAO.findAllByRid(rid);
//    }
//
////    @Modifying
//    @Transactional
//    public void savePermChanges(int rid, List<AdminPermission> perms) {
//        adminRolePermissionDAO.deleteAllByRid(rid);
//        List<AdminRolePermission> rps = new ArrayList<>();
//        perms.forEach(p -> {
//            AdminRolePermission rp = new AdminRolePermission();
//            rp.setRid(rid);
//            rp.setPid(p.getId());
//            rps.add(rp);
//        });
//        adminRolePermissionDAO.saveAll(rps);
//    }
//}
