package com.codedream.peripheral.realm;

import com.codedream.intf.entity.User;

//import com.codedream.intf.service.AdminPermissionService;
import com.codedream.intf.service.AdminRoleService;
import com.codedream.intf.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;


public class WJRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
//    @Autowired
//    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminRoleService adminRoleService;

//    // 重写获取授权信息方法
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        // 获取当前用户的所有权限
//        String username = principalCollection.getPrimaryPrincipal().toString();
//        Set<String> permissions =new HashSet<>();
//
////                adminPermissionService.listPermissionURLsByUser(username);
//
//        // 将权限放入授权信息中
//        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
//        s.setStringPermissions(permissions);
//        return s;
//    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();

        return s;
    }
    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        User user = userService.findByUsername(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
