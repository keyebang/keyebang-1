package com.shg.keyebang.model;

import java.util.Calendar;

import cn.bmob.v3.BmobObject;

public class Todo extends BmobObject {
    private String todoTitle;
    private String todoMessage;
    private int color;
    private Calendar date;

    public Todo(String todoTitle, String todoMessage, Calendar date, int color) {
        this.todoTitle = todoTitle;
        this.todoMessage = todoMessage;
        this.date = date;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Todo setColor(int color) {
        this.color = color;
        return this;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public Todo setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
        return this;
    }

    public String getTodoMessage() {
        return todoMessage;
    }

    public Todo setTodoMessage(String todoMessage) {
        this.todoMessage = todoMessage;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public Todo setDate(Calendar date) {
        this.date = date;
        return this;
    }
}
