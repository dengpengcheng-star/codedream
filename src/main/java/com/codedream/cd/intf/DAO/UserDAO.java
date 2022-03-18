package com.codedream.cd.intf.DAO;

import com.codedream.cd.intf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DengPengcheng
 * @create 2022/3/913:22
 */

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);


}
