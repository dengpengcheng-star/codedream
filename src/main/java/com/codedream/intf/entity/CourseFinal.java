package com.codedream.intf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "course_final")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CourseFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String courseName;
    String picSrc;
}
