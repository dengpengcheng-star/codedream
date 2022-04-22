package com.codedream.intf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@ToString
@Getter
@Setter
@Entity
@Table(name = "user_course_relation_copy1")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int userId;
    int courseId;
    int Completion;
    @Column(name = "date")
    Date date;
}
