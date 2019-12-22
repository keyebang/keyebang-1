package com.shg.keyebang.services.datamodel;

import cn.bmob.v3.BmobObject;

public class Evaluation extends BmobObject {

    private String content;
    private int likes;
    private Course courseId;


    public Evaluation(){

    }
    public Evaluation(String content){
        this.content=content;
    }

    public Evaluation setContent(String content){
        this.content=content;
        return this;
    }
    public String getContent(){return content;}
    public int getLikes(){return likes;}
    public Evaluation setLikes(int likes){
        this.likes=likes;
        return this;
    }

    public Course getCourseId(){return courseId;}
    public Evaluation setCourseId(Course courseId){
        this.courseId=courseId;
        return this;
    }


}
