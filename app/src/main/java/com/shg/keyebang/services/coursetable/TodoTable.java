package com.shg.keyebang.services.coursetable;


public class TodoTable {

    /*public static void setTodo(final ViewTodo todo, String classId, CourseTableListener listener){



        todo.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null) { listener.onSuccess("添加数据成功" +s);}
                else{listener.onFailure("添加数据失败：" + e.getErrorCode()+ "-" + e.getMessage() + "\n");}
            }

        });
    }

    public static void deleteTodo(ViewCourse course, GetTodoListener listener){

        course.setTodoTitle(null);
        course.setTodoMessage(null);
        course.setYear(0);
        course.setDayOfMonth(0);
        course.setMonth(0);
        course.update( new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("更新成功");
                }else{
                    listener.onFailure("更新失败：" + e.getMessage());
                }
            }

        });
    }*/
}
