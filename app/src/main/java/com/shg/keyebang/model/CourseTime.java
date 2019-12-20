package com.shg.keyebang.model;

public class CourseTime {
    private int weekday;
    private int firstClass;
    private int lastClass;

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
}
