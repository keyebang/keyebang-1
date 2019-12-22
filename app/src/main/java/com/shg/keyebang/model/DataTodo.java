package com.shg.keyebang.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shg.keyebang.R;
import com.shg.keyebang.services.datamodel.Course;
import com.shg.keyebang.services.datamodel.CourseTime;
import com.shg.keyebang.services.datamodel.Todo;

import java.util.Calendar;

public class DataTodo {
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



    public DataTodo(String todoTitle, String todoMessage, Calendar date, int color) {

        this.todoTitle = todoTitle;
        this.todoMessage = todoMessage;
        this.date = date;
        this.color = color;
    }

    public DataTodo() {

    }

    public int getColor() {
        return color;
    }

    public DataTodo setColor(@NonNull int color) {
        this.color = color;
        return this;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public DataTodo setTodoTitle(@NonNull String todoTitle) {
        this.todoTitle = todoTitle;
        return this;
    }

    public String getTodoMessage() {
        return todoMessage;
    }

    public DataTodo setTodoMessage(@Nullable String todoMessage) {
        this.todoMessage = todoMessage;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public DataTodo setDate(@NonNull Calendar date) {
        this.date = date;
        return this;
    }

    public User getUserId(){return userId;}
    public DataTodo setUserId(User userId){
        this.userId=userId;
        return this;
    }
    public Course getCourseId(){return courseId;}
    public DataTodo setCourseId(Course courseId){
        this.courseId=courseId;
        return this;
    }
    public CourseTime getTimeId(){return timeId;}
    public DataTodo setTimeId(CourseTime timeId){
        this.timeId=timeId;
        return this;
    }
}
