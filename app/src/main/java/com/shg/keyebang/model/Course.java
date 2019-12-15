package com.shg.keyebang.model;

import java.util.Calendar;

import cn.bmob.v3.BmobObject;

public class Course extends BmobObject {
    private String className;
    private String classPlace;
    private String teacher;
    private int weekday;
    private int firstClass;
    private int lastClass;
    private String todoTitle;
    private String todoMessage;
    private Calendar date;
    private int year;
    private int month;
    private int dayOfMonth;
    private User student;


    public Course(String className, String classPlace, String teacher, int weekday, int firstClass, int lastClass) {
        this.className = className;
        this.classPlace = classPlace;
        this.teacher = teacher;
        this.weekday = weekday;
        this.firstClass = firstClass;
        this.lastClass = lastClass;
    }

    public User getStudent(){
        return student;
    }

    public Course setStudent(User student){
        this.student=student;
        return this;
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
    public String getTodoTitle() {
        return todoTitle;
    }

    public Course setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
        return this;
    }

    public String getTodoMessage() {
        return todoMessage;
    }

    public Course setTodoMessage(String todoMessage) {
        this.todoMessage = todoMessage;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public Course setDate(Calendar date) {
        this.date = date;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Course setYear(int year){
        this.year=year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Course setMonth(int month){
        this.month=month;
        return this;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public Course setDayOfMonth(int dayOfMonth){
        this.dayOfMonth=dayOfMonth;
        return this;
    }
}
