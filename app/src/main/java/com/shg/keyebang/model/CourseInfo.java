package com.shg.keyebang.model;

import cn.bmob.v3.BmobObject;

public class CourseInfo extends BmobObject {
    private String classId;
    private String className;
    private String classPlace;
    private String teacher;
    private int weekday;
    private int firstClass;
    private int lastClass;

    public CourseInfo(String classId, String className, String classPlace, String teacher, int weekday, int firstClass, int lastClass) {
        this.classId =classId;
        this.className = className;
        this.classPlace = classPlace;
        this.teacher = teacher;
        this.weekday = weekday;
        this.firstClass = firstClass;
        this.lastClass = lastClass;
    }

    public CourseInfo setClassId(String classId){
        this.classId=classId;
        return this;
    }
    public String getClassId(){return classId;}

}
