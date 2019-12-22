package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.services.DeleteListener;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.services.datamodel.Todo;

import java.util.Calendar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class TodoService {
    public static void deleteTodo(String todoId, AddDataListener listener){
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

    public static void saveTodo(ViewTodo viewTodo,SaveTodoListener listener) {
        if(StringUtil.isAllNullOrEmpty(viewTodo.getTodoId())) return;
        else {
            Calendar calendar =viewTodo.getDate();
            final Todo todo=new Todo();
            todo.setObjectId(viewTodo.getTodoId());
            todo.setTodoTitle(viewTodo.getTodoTitle());
            todo.setTodoMessage(viewTodo.getTodoMessage());
            todo.setColor(viewTodo.getColor());
            todo.setYear(calendar.get(Calendar.YEAR));
            todo.setMonth(calendar.get(Calendar.MONTH)+1);
            todo.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));

            todo.update(new UpdateListener(){
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        listener.onSuccess("添加数据成功",todo.getObjectId());
                    }else{
                        listener.onFailure("创建数据失败：" + e.getMessage());
                    }
                }
            });
        }
    }

    public static void deleteCourse(String todoId, DeleteListener listener) {
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
