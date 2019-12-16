package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class CourseTable {


    public static void setClass(final Course course , CourseTableListener listener){

        User user= BmobUser.getCurrentUser(User.class);
        course.setStudent(user);//关联到user类

        course.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if (e == null) { listener.onSuccess("添加数据成功" +s);}
                else{listener.onFailure("添加数据失败：" + e.getErrorCode()+ "-" + e.getMessage() + "\n");}
            }

        });

    }

    public static void getClass(GetClassListener listener){
        BmobQuery<Course> query =new BmobQuery<>();
        query.addWhereEqualTo("student",BmobUser.getCurrentUser(User.class));
        query.include("student");
        query.setLimit(20);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> object, BmobException e) {
                if(e==null){

                    Map<Course, Todo> classTable = new HashMap<>();
                    for (Course course : object) {
                        Course course1 = new Course(course.getClassName(), course.getClassPlace(),course.getTeacher(),course.getWeekday(),course.getFirstClass(),course.getLastClass());
                        if(course.getTodoTitle()==null){classTable.put(course1, null);}
                        else{
                            Calendar calendar = new GregorianCalendar(course.getYear(),course.getMonth(), course.getDayOfMonth());
                            Todo todo = new Todo(course.getTodoTitle(),course.getTodoMessage(),calendar,Todo.COLOR_RED);
                            classTable.put(course1,todo);
                        }

                    }
                    listener.onSuccess(classTable);
                }else{
                    listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());
                }
            }
        });
    }
}
