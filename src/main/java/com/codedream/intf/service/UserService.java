package com.codedream.intf.service;


import com.codedream.intf.DAO.UserDAO;
import com.codedream.intf.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService{

    @Autowired
    UserDAO userDAO;


    public boolean isExist(String username) {
        User user = userDAO.findByUsername(username);
        return null != user;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public int register(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
//        user.setEnabled(true);

        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userDAO.save(user);

        return 1;
    }

//    public void updateUserStatus(User user) {
//        User userInDB = userDAO.findByUsername(user.getUsername());
//        userInDB.setEnabled(user.isEnabled());
//        userDAO.save(userInDB);
//    }
    public Map<String, String> getInfo(){
        Object principal= SecurityUtils.getSubject().getPrincipal();

        String username = ((User)principal).getUsername();


        User user =findByUsername(username);
        Map<String, String> map=new HashMap<>();
        map.put("name",user.getName());
        map.put("username",username);
        map.put("email",user.getEmail());
        map.put("phone",user.getPhone());
        return map;
    }
    public int changeInfo(User u,String password){
        Object principal= SecurityUtils.getSubject().getPrincipal();

        String username = ((User)principal).getUsername();


        User user =findByUsername(username);
        String encodedPassword = new SimpleHash("md5", password, u.getSalt(), 2).toString();
        if(!encodedPassword.equals(user.getPassword())){
            return -1;
        }
        resetPassword(user);

        return 0;
    }
    public User resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userDAO.save(userInDB);
    }

//    public void editUser(User user) {
//        User userInDB = userDAO.findByUsername(user.getUsername());
//        userInDB.setName(user.getName());
//        userInDB.setPhone(user.getPhone());
//        userInDB.setEmail(user.getEmail());
//        userDAO.save(userInDB);
//        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
//    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }


}
