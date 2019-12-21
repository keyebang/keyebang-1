package com.shg.keyebang.model;

import java.util.ArrayList;

public class ViewCourseSelect {
    private String courseId;
    private String teacher;
    private String coursePlace;
    private ArrayList<ViewCourseTime> courseTimes;

    public ViewCourseSelect() {
    }

    public ViewCourseSelect(String courseId, String teacher, String coursePlace, ArrayList<ViewCourseTime> courseTimes) {
        this.courseId = courseId;
        this.teacher = teacher;
        this.coursePlace = coursePlace;
        this.courseTimes = courseTimes;
    }

    public static ViewCourseSelect builder(){
        return new ViewCourseSelect();
    }

    public String getCourseId() {
        return courseId;
    }

    public ViewCourseSelect setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getTeacher() {
        return teacher;
    }

    public ViewCourseSelect setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public ViewCourseSelect setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
        return this;
    }

    public ArrayList<ViewCourseTime> getCourseTimes() {
        return courseTimes;
    }

    public ViewCourseSelect setCourseTimes(ArrayList<ViewCourseTime> courseTimes) {
        this.courseTimes = courseTimes;
        return this;
    }
}
