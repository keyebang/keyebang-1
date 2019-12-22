package com.shg.keyebang.model;


import java.util.ArrayList;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import cn.bmob.v3.BmobObject;

public class ViewCourse {
    private String todoId;
    private String courseId;
    private String courseName;
    private String classPlace;
    private String teacher;
    private String campus;
    private String type;
    private String info;
    private float credit;
    private int oneOfWeekday;
    private int oneOfFirstClass;
    private int oneOfLastClass;
    private ArrayList<ViewCourseTime> courseTimes;


    public ViewCourse(){

    }

    public ViewCourse(String courseName, String classPlace, String teacher, int oneOfWeekday, int oneOfFirstClass, int oneOfLastClass) {
        this.courseName = courseName;
        this.classPlace = classPlace;
        this.teacher = teacher;
        this.oneOfWeekday = oneOfWeekday;
        this.oneOfFirstClass = oneOfFirstClass;
        this.oneOfLastClass = oneOfLastClass;
    }

    public ViewCourse(String courseId, String courseName, String classPlace, String teacher, ArrayList<ViewCourseTime> courseTimes) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.classPlace = classPlace;
        this.teacher = teacher;
        this.courseTimes = courseTimes;
    }


    @NotNull
    @Contract(" -> new")
    public static ViewCourse builder(){
        return new ViewCourse();
    }

    public String getCampus() {
        return campus;
    }

    public ViewCourse setCampus(String campus) {
        this.campus = campus;
        return this;
    }

    public float getCredit() {
        return credit;
    }

    public ViewCourse setCredit(float credit) {
        this.credit = credit;
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public ViewCourse setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public ViewCourse setClassPlace(String classPlace) {
        this.classPlace = classPlace;
        return this;
    }

    public String getTeacher() {
        return teacher;
    }

    public ViewCourse setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public int getOneOfWeekday() {
        return oneOfWeekday;
    }

    public ViewCourse setOneOfWeekday(int oneOfWeekday) {
        this.oneOfWeekday = oneOfWeekday;
        return this;
    }

    public int getOneOfFirstClass() {
        return oneOfFirstClass;
    }

    public ViewCourse setOneOfFirstClass(int oneOfFirstClass) {
        this.oneOfFirstClass = oneOfFirstClass;
        return this;
    }

    public int getOneOfLastClass() {
        return oneOfLastClass;
    }

    public ViewCourse setOneOfLastClass(int oneOfLastClass) {
        this.oneOfLastClass = oneOfLastClass;
        return this;
    }

    public String getType() {
        return type;
    }

    public ViewCourse setType(String type) {
        this.type = type;
        return this;
    }

    public ArrayList<ViewCourseTime> getCourseTimes() {
        return courseTimes;
    }

    public ViewCourse setCourseTimes(ArrayList<ViewCourseTime> courseTimes) {
        this.courseTimes = courseTimes;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public ViewCourse setCourseId(String courseId){
        this.courseId = courseId;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public ViewCourse setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getTodoId() {
        return todoId;
    }

    public ViewCourse setTodoId(String todoId) {
        this.todoId = todoId;
        return this;
    }
}
