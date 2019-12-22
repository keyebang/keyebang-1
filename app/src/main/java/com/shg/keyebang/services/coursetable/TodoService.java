package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.services.DeleteListener;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.services.datamodel.Todo;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class TodoService {
    public void deleteTodo(String todoId, AddDataListener listener){
        final Todo todo=new Todo();
        todo.setObjectId(todoId);
        todo.setTodoTitle(null);
        todo.setTodoMessage(null);
        todo.update(new UpdateListener(){
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });

    }

    public void saveTodo(ViewTodo viewTodo,AddDataListener listener) {
        if(viewTodo.getTodoId() == null) return;
        else {
            final Todo todo=new Todo();
            todo.setObjectId(viewTodo.getTodoId());
            todo.setTodoTitle(viewTodo.getTodoTitle());
            todo.setTodoMessage(viewTodo.getTodoMessage());

            todo.update(new UpdateListener(){
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        listener.onSuccess("添加数据成功");
                    }else{
                        listener.onFailure("创建数据失败：" + e.getMessage());
                    }
                }
            });
        }
    }

    public void deleteCourse(String todoId, DeleteListener listener) {
        Todo todo=new Todo();

        todo.delete(todoId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("删除数据成功");
                }else{listener.onFailure("删除数据失败：" + e.getMessage());}

            }
        });
    }
}
