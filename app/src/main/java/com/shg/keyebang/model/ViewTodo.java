package com.shg.keyebang.model;

import com.shg.keyebang.R;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.bmob.v3.BmobObject;

public class ViewTodo {
    public static int COLOR_RED = R.color.cardColorRed;
    public static int COLOR_BLUE = R.color.cardColorBlue;
    public static int COLOR_GREEN = R.color.cardColorGreen;

    private String todoId;
    private String todoTitle;
    private String todoMessage;
    private int color;
    private Calendar date;

    public ViewTodo(String todoId, String todoTitle, String todoMessage, Calendar date, int color) {
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.todoMessage = todoMessage;
        this.date = date;
        this.color = color;
    }

    public ViewTodo() {

    }

    public int getColor() {
        return color;
    }

    public ViewTodo setColor(@NonNull int color) {
        this.color = color;
        return this;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public ViewTodo setTodoTitle(@NonNull String todoTitle) {
        this.todoTitle = todoTitle;
        return this;
    }

    public String getTodoMessage() {
        return todoMessage;
    }

    public ViewTodo setTodoMessage(@Nullable String todoMessage) {
        this.todoMessage = todoMessage;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public ViewTodo setDate(@NonNull Calendar date) {
        this.date = date;
        return this;
    }

    public String getTodoId() {
        return todoId;
    }

    public ViewTodo setTodoId(String todoId) {
        this.todoId = todoId;
        return this;
    }
}
