package com.shg.keyebang.presenter.coursetable;

import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.coursetable.CourseTableFragment;
import com.shg.keyebang.view.activity.coursetable.TodoDialog;

public class TodoPresenter extends BasePresenter {
    private TodoDialog todoDialog;
    private CourseTableFragment fragment;

    public TodoPresenter(TodoDialog todoDialog, CourseTableFragment fragment){
        this.todoDialog = todoDialog;
        this.fragment = fragment;
    }

    public void deleteTodo(String todoId) {
        fragment.updateTodoInCourseCard(todoId, null);
    }

    public void saveTodo(ViewTodo todo) {
        if(todo.getTodoId() == null) return;
        fragment.updateTodoInCourseCard(todo.getTodoId(), todo);
    }

    public void deleteCourse() {
    }
}
