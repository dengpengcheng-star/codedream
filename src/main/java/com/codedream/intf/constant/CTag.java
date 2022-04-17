package com.codedream.intf.constant;

/**
 * @author DengPengcheng
 * @create 2022/4/1715:46
 */

public enum CTag {
    JAVA(1,"java"),
    JAVASCRIPT(2,"JAVASCRIPT"),
    ANDROID(3,"ANDROID"),
    PYTHON(4,"PYTHON"),
    SPRING(5,"SPRING"),
    USEFULSKILL(6,"USEFULSKILL"),    //实用技能 制作简历，视频剪辑等
    DATABASE(7,"DATABASE");
    int id;
    String name;
    CTag(int id, String name){
        this.id=id;
        this.name=name;
    }
}