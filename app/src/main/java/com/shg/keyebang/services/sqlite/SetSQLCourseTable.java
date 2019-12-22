package com.shg.keyebang.services.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.db.CourseTableDBDao;
import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.services.coursetable.GetClassListener;
import com.shg.keyebang.services.datamodel.Course;
import com.shg.keyebang.services.datamodel.CourseTime;
import com.shg.keyebang.services.datamodel.Todo;
import com.shg.keyebang.services.sqlitemodel.CourseTableDB;

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

public class SetSQLCourseTable {



    private static SQLiteDBHelper dbHelper;


    //添加
    public static void addClass(String courseId, SQLiteListener listener) {

        dbHelper.getReadableDB();
        BmobQuery<Course> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("objectId", courseId.substring(1,courseId.length()));
        query1.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> courses, BmobException e) {
                if (e == null) {
                    for (Course course : courses) {
                        BmobQuery<CourseTime> query3 = new BmobQuery<>();
                        query3.addWhereEqualTo("courseId", courseId);
                        query3.findObjects(new FindListener<CourseTime>() {
                            @Override
                            public void done(List<CourseTime> courseTimes, BmobException e) {
                                if (e == null) {
                                    dbHelper.insertCourse(course,courseTimes);
                                }
                                else{
                                    listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());
                                }
                            }
                        });
                    }
                }
                else{
                    listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());
                }
            }
        });
    }

    //删除
    public static void deleteClass(String courseId, GetClassListener listener) {
        Long courseId1=Long.parseLong(courseId);
        dbHelper.deleteCourse(courseId1);
    }



}