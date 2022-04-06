package com.codedream.intf.DAO;

import com.codedream.intf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);


}
