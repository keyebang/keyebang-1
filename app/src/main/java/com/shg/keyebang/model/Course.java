package com.shg.keyebang.model;

public class Course {
    private String className;
    private String classPlace;
    private String teacher;
    private int weekday;
    private int firstClass;
    private int lastClass;

    public Course(String className, String classPlace, String teacher, int weekday, int firstClass, int lastClass) {
        this.className = className;
        this.classPlace = classPlace;
        this.teacher = teacher;
        this.weekday = weekday;
        this.firstClass = firstClass;
        this.lastClass = lastClass;
    }

    public String getClassName() {
        return className;
    }

    public Course setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public Course setClassPlace(String classPlace) {
        this.classPlace = classPlace;
        return this;
    }

    public String getTeacher() {
        return teacher;
    }

    public Course setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public int getWeekday() {
        return weekday;
    }

    public Course setWeekday(int weekday) {
        this.weekday = weekday;
        return this;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public Course setFirstClass(int firstClass) {
        this.firstClass = firstClass;
        return this;
    }

    public int getLastClass() {
        return lastClass;
    }

    public Course setLastClass(int lastClass) {
        this.lastClass = lastClass;
        return this;
    }
}
