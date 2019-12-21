package com.shg.keyebang.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class ViewCourseTime {
    public static final int WEEK_ALL = 0;
    public static final int WEEK_SINGLE = 1;
    public static final int WEEK_DOUBLE = 2;

    @IntDef({WEEK_ALL, WEEK_SINGLE, WEEK_DOUBLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SingleDoubleWeek {}

    private String timeId;
    private String courseId;
    private int weekday;
    private int firstClass;
    private int lastClass;
    private int singleOrDouble;

    public ViewCourseTime(){}

    public ViewCourseTime(int weekday, int firstClass, int lastClass) {
        this.weekday = weekday;
        this.firstClass = firstClass;
        this.lastClass = lastClass;
    }

    public static ViewCourseTime builder(){
        return new ViewCourseTime();
    }


    public int getWeekday() {
        return weekday;
    }

    public ViewCourseTime setWeekday(int weekday) {
        this.weekday = weekday;
        return this;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public ViewCourseTime setFirstClass(int firstClass) {
        this.firstClass = firstClass;
        return this;
    }

    public int getLastClass() {
        return lastClass;
    }

    public ViewCourseTime setLastClass(int lastClass) {
        this.lastClass = lastClass;
        return this;
    }

    public String getTimeId() {
        return timeId;
    }

    public ViewCourseTime setTimeId(String timeId) {
        this.timeId = timeId;
        return this;
    }

    public int getSingleOrDouble() {
        return singleOrDouble;
    }

    public ViewCourseTime setSingleOrDouble(@SingleDoubleWeek int singleOrDouble) {
        this.singleOrDouble = singleOrDouble;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public ViewCourseTime setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

}
