package com.codedream.intf.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


/**
 * @author DengPengcheng
 * @create 2022/3/815:36
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
/**
 * 因为是做前后端分离，而前后端数据交互用的是 json 格式。
 * 那么 User 对象就会被转换为 json 数据。
 * 而本项目使用 jpa 来做实体类的持久化，jpa 默认会使用 hibernate,
 * 在 jpa 工作过程中，就会创造代理类来继承 User ，并添加 handler 和 hibernateLazyInitializer 这两个无须 json 化的属性，
 * 所以这里需要用 JsonIgnoreProperties 把这两个属性忽略掉。
 */
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Username.
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * Password.
     */
    private String password;

    /**
     * Salt for encoding.
     */
    private String salt;

    /**
     * Real name.
     */
    private String name;

    /**
     * Phone number.
     */
    private String phone;

    /**
     * Email address.
     *
     * A Email address can be null,but should be correct if exists.
     */
    @Email(message = "请输入正确的邮箱")
    private String email;

    private int gender;

    private int course_taken;
    private int learning_exp;


}


