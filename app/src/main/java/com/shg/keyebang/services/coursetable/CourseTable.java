package com.shg.keyebang.services.coursetable;

import android.util.Log;

import com.shg.keyebang.aatools.IdUtil;
import com.shg.keyebang.aatools.StringUtil;
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

import static android.content.ContentValues.TAG;


public class CourseTable {
    public static void getClass(GetClassListener listener) {
        Map<ViewCourse, ViewTodo> courseTable = new HashMap<>();
        BmobQuery<Todo> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("userId", BmobUser.getCurrentUser(User.class).getObjectId());
        query1.setLimit(30);
        query1.findObjects(new FindListener<Todo>() {

            @Override
            public void done(List<Todo> object, BmobException e) {
                if (e == null) {
                    if(object.size() == 0) listener.onSuccess(courseTable);
                    for (Todo todo : object) {
                        BmobQuery<Course> query2 = new BmobQuery<>();
                        query2.addWhereEqualTo("objectId",IdUtil.getCorrectId(todo.getCourseId()));
                        query2.findObjects(new FindListener<Course>() {
                            @Override
                            public void done(List<Course> object, BmobException e) {
                                if (e == null) {
                                    if(object.size() == 0) listener.onSuccess(courseTable);
                                    for (Course course : object) {
                                        ArrayList<ViewCourseTime> courseTimes = new ArrayList<>();
                                        BmobQuery<CourseTime> query3 = new BmobQuery<>();
                                        query3.addWhereEqualTo("courseId", todo.getCourseId());
                                        query3.findObjects(new FindListener<CourseTime>() {
                                            @Override
                                            public void done(List<CourseTime> object, BmobException e) {
                                                if (e == null) {
                                                    if(object.size() == 0) listener.onSuccess(courseTable);
                                                    for (CourseTime courseTime : object) {
                                                        ViewCourseTime viewCourseTime = ViewCourseTime.builder()
                                                                .setTimeId(courseTime.getObjectId())
                                                                .setWeekday(courseTime.getWeekday())
                                                                .setFirstClass(courseTime.getFirstClass())
                                                                .setLastClass(courseTime.getLastClass())
                                                                .setSingleOrDouble(courseTime.getWeekTime());
                                                        courseTimes.add(viewCourseTime);
                                                        ViewCourse viewCourse = new ViewCourse(course.getObjectId(), course.getClassName(), course.getClassPlace(), course.getTeacher(), courseTimes);
                                                        viewCourse.setTodoId(todo.getObjectId());
                                                        if (StringUtil.isAllNullOrEmpty(todo.getTodoTitle())) {
                                                            courseTable.put(viewCourse, null);
                                                        } else {
                                                            Calendar calendar = new GregorianCalendar(todo.getYear(), todo.getMonth(), todo.getDayOfMonth());
                                                            ViewTodo viewTodo = new ViewTodo(todo.getObjectId(), todo.getTodoTitle(), todo.getTodoMessage(), calendar, todo.getColor());
                                                            courseTable.put(viewCourse, viewTodo);
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
