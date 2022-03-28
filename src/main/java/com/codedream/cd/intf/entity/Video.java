package com.codedream.cd.intf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "video")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String videoSrc;
    String name;
    String teacher;
    Date date;
    String abs;
    String coverSrc;
}
