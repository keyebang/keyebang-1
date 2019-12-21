package com.shg.keyebang.services.datamodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shg.keyebang.R;
import com.shg.keyebang.model.User;

import java.util.Calendar;

import cn.bmob.v3.BmobObject;

public class Todo extends BmobObject {
    public static int COLOR_RED = R.color.cardColorRed;
    public static int COLOR_BLUE = R.color.cardColorBlue;
    public static int COLOR_GREEN = R.color.cardColorGreen;

    private String todoTitle;
    private String todoMessage;
    private int color;
    private Calendar date;
    private User userId;
    private Course courseId;
    private CourseTime timeId;
    private int year;
    private int month;
    private int dayOfMonth;



    public Todo(String todoTitle, String todoMessage, Calendar date, int color) {

        this.todoTitle = todoTitle;
        this.todoMessage = todoMessage;
        this.date = date;
        this.color = color;
    }

    public Todo() {

    }

    public int getColor() {
        return color;
    }

    public Todo setColor(@NonNull int color) {
        this.color = color;
        return this;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public Todo setTodoTitle(@NonNull String todoTitle) {
        this.todoTitle = todoTitle;
        return this;
    }

    public String getTodoMessage() {
        return todoMessage;
    }

    public Todo setTodoMessage(@Nullable String todoMessage) {
        this.todoMessage = todoMessage;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public Todo setDate(@NonNull Calendar date) {
        this.date = date;
        return this;
    }

    public User getUserId(){return userId;}
    public Todo setUserId(User userId){
        this.userId=userId;
        return this;
    }
    public Course getCourseId(){return courseId;}
    public Todo setCourseId(Course courseId){
        this.courseId=courseId;
        return this;
    }
    public CourseTime getTimeId(){return timeId;}
    public Todo setTimeId(CourseTime timeId){
        this.timeId=timeId;
        return this;
    }
    public int getYear(){return year;}
    public Todo setYear(int year){
        this.year=year;
        return this;
    }
    public int getMonth(){return month;}
    public Todo setMonth(int month){
        this.month=month;
        return this;
    }
    public int getDayOfMonth(){return dayOfMonth;}
    public Todo setDayOfMonth(int dayOfMonth){
        this.dayOfMonth=dayOfMonth;
        return this;
    }
}
