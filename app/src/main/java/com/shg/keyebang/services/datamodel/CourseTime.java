package com.shg.keyebang.services.datamodel;

import cn.bmob.v3.BmobObject;

public class CourseTime extends BmobObject {
    private int weekday;
    private int firstClass;
    private int lastClass;
    private int weekTime;
    private String courseId;

    public CourseTime(){}

    public CourseTime(int weekday, int firstClass, int lastClass) {
        this.weekday = weekday;
        this.firstClass = firstClass;
        this.lastClass = lastClass;
    }

    public static CourseTime builder(){
        return new CourseTime();
    }


    public int getWeekday() {
        return weekday;
    }

    public CourseTime setWeekday(int weekday) {
        this.weekday = weekday;
        return this;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public CourseTime setFirstClass(int firstClass) {
        this.firstClass = firstClass;
        return this;
    }

    public int getLastClass() {
        return lastClass;
    }

    public CourseTime setLastClass(int lastClass) {
        this.lastClass = lastClass;
        return this;
    }

    public int getWeekTime() {
        return weekTime;
    }

    public CourseTime setWeekTime(int weekTime) {
        this.weekTime = weekTime;
        return this;
    }


    public String getCourseId(){return courseId;}
    public CourseTime setCourseId(String courseId){
        this.courseId=courseId;
        return this;
    }

}