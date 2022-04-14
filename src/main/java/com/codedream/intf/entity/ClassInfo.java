package com.codedream.intf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "class_info")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ClassInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int cid;
    int uid;
    String name;
    @Transient
    private String up;//boolean
}
