package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.services.datamodel.Course;
import com.shg.keyebang.services.datamodel.CourseTime;
import com.shg.keyebang.services.datamodel.Todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.FindListener;


public class CourseTable {


    /*public static void setClass(final ViewCourse course , CourseTableListener listener){

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
        BmobQuery<ViewCourse> query =new BmobQuery<>();
        query.addWhereEqualTo("student",BmobUser.getCurrentUser(User.class));
        query.include("student");
        query.setLimit(20);
        query.findObjects(new FindListener<ViewCourse>() {
            @Override
            public void done(List<ViewCourse> object, BmobException e) {
                if(e==null){

                    Map<ViewCourse, ViewTodo> classTable = new HashMap<>();
                    for (ViewCourse course : object) {
                        ViewCourse course1 = new ViewCourse(course.getCourseName(), course.getClassPlace(),course.getTeacher(),course.getOneOfWeekday(),course.getOneOfFirstClass(),course.getOneOfLastClass());
                        if(course.getTodoTitle()==null){classTable.put(course1, null);}
                        else{
                            Calendar calendar = new GregorianCalendar(course.getYear(),course.getMonth(), course.getDayOfMonth());
                            ViewTodo todo = new ViewTodo(course.getTodoTitle(),course.getTodoMessage(),calendar,ViewTodo.COLOR_RED);
                            classTable.put(course1,todo);
                        }

                    }
                    listener.onSuccess(classTable);
                }else{
                    listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());
                }
            }
        });
    }*/
    public static void getClass(GetClassListener listener) {
        BmobQuery<Todo> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("userId", BmobUser.getCurrentUser(User.class));
        query1.include("courseId[className|classPlace|teacher].timeId[courseId|weekday|firstClass|LastClass]");

        query1.setLimit(30);
        query1.findObjects(new FindListener<Todo>() {

            @Override
            public void done(List<Todo> object, BmobException e) {
                if (e == null) {
                    Map<ViewCourse, ViewTodo> courseTable = new HashMap<>();
                    for (Todo todo : object) {
                        BmobQuery<Course> query2 = new BmobQuery<>();
                        query2.addWhereEqualTo("courseId", todo.getCourseId());
                        query2.findObjects(new FindListener<Course>() {
                            @Override
                            public void done(List<Course> object, BmobException e) {
                                if (e == null) {
                                    for (Course course : object) {
                                        BmobQuery<CourseTime> query3 = new BmobQuery<>();
                                        query3.addWhereEqualTo("courseId", todo.getCourseId());
                                        ArrayList<ViewCourseTime> courseTimes1 = new ArrayList<>();
                                        query3.findObjects(new FindListener<CourseTime>() {
                                            @Override
                                            public void done(List<CourseTime> object, BmobException e) {
                                                if (e == null) {
                                                    for (CourseTime courseTime : object) {

                                                        ViewCourseTime courseTime1 = ViewCourseTime.builder()
                                                                .setTimeId(courseTime.getObjectId())
                                                                .setWeekday(courseTime.getWeekday())
                                                                .setFirstClass(courseTime.getFirstClass())
                                                                .setLastClass(courseTime.getLastClass())
                                                                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);
                                                        courseTimes1.add(courseTime1);
                                                        ViewCourse viewCourse1 = new ViewCourse(course.getObjectId(), course.getClassName(), course.getClassPlace(), course.getTeacher(), courseTimes1);
                                                        if (todo.getTodoTitle() == null) {
                                                            courseTable.put(viewCourse1, null);
                                                        } else {
                                                            Calendar calendar = new GregorianCalendar(todo.getYear(), todo.getMonth(), todo.getDayOfMonth());
                                                            ViewTodo viewTodo1 = new ViewTodo(todo.getObjectId(), todo.getTodoTitle(), todo.getTodoMessage(), calendar, todo.getColor());
                                                            courseTable.put(viewCourse1, viewTodo1);
                                                        }
                                                    }
                                                    listener.onSuccess(courseTable);
                                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                                            }
                                        });
                                    }
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}


                            }
                        });
                    }
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }
        });
    }

}
