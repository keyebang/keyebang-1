package com.shg.keyebang.presenter.coursetable;

import com.shg.keyebang.model.Todo;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.coursetable.TodoDialog;

public class TodoPresenter extends BasePresenter {
    private TodoDialog todoDialog;

    public TodoPresenter(TodoDialog todoDialog){
        this.todoDialog = todoDialog;
    }

    public void deleteTodo() {
    }

    public void saveTodo(Todo todo) {
    }

    public void deleteCourse() {
    }
}
