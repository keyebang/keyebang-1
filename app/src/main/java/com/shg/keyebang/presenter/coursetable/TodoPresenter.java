package com.shg.keyebang.presenter.coursetable;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.services.coursetable.SaveTodoListener;
import com.shg.keyebang.services.coursetable.TodoService;
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
        if(todoId != null){
            TodoService.deleteTodo(todoId, new AddDataListener() {
                @Override
                public void onSuccess(String message) {
                    fragment.updateTodoInCourseCard(todoId, null);
                }

                @Override
                public void onFailure(String errMassage) {
                    fragment.showErrorMessage(errMassage);
                }
            });
        }
        else fragment.showErrorMessage("删除的Todo不存在");
    }

    public void saveTodo(String courseId, ViewTodo todo) {
        if(todo.getTodoId() == null) return;
        TodoService.saveTodo(todo, new SaveTodoListener() {
            @Override
            public void onSuccess(String message, String objectId) {
                if(!StringUtil.isAllNullOrEmpty(todo.getTodoId()) && !todo.getTodoId().equals(objectId)){
                    fragment.showErrorMessage("todoId异常");
                }
                else{
                    todo.setTodoId(objectId);
                    fragment.updateTodoInCourseCard(courseId, todo);
                }
            }

            @Override
            public void onFailure(String errMassage) {
                fragment.showErrorMessage(errMassage);
            }
        });
    }

    public void deleteCourse() {
    }
}
