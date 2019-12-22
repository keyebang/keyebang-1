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
