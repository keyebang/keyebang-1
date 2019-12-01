package com.shg.keyebang.model;

import java.util.Calendar;
import java.util.Date;

public class Todo {
    private String todoTitle;
    private String todoMessage;
    private Calendar date;

    public Todo(String todoTitle, String todoMessage, Calendar date) {
        this.todoTitle = todoTitle;
        this.todoMessage = todoMessage;
        this.date = date;
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
