package com.codedream.intf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "lecture")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String name;
    String abs;
    int cid;
    int teacher_id;
    String coverSrc;
}
