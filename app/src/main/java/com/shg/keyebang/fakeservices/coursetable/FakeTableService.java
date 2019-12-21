package com.shg.keyebang.fakeservices.coursetable;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.ViewTodo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class FakeTableService {
    public static void getTable(String studentId, FakeGetTableListener listener){

        boolean success = true;

        ViewCourseTime courseTime1 = ViewCourseTime.builder()
                .setTimeId("1")
                .setWeekday(1)
                .setFirstClass(1)
                .setLastClass(2)
                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);

        ViewCourseTime courseTime2 = ViewCourseTime.builder()
                .setTimeId("2")
                .setWeekday(4)
                .setFirstClass(1)
                .setLastClass(2)
                .setSingleOrDouble(ViewCourseTime.WEEK_DOUBLE);

        ViewCourseTime courseTime3 = ViewCourseTime.builder()
                .setTimeId("3")
                .setWeekday(2)
                .setFirstClass(3)
                .setLastClass(4)
                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);

        ViewCourseTime courseTime4 = ViewCourseTime.builder()
                .setTimeId("4")
                .setWeekday(3)
                .setFirstClass(3)
                .setLastClass(4)
                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);

        ViewCourseTime courseTime5 = ViewCourseTime.builder()
                .setTimeId("5")
                .setWeekday(4)
                .setFirstClass(3)
                .setLastClass(4)
                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);

        ViewCourseTime courseTime6 = ViewCourseTime.builder()
                .setTimeId("6")
                .setWeekday(5)
                .setFirstClass(3)
                .setLastClass(4)
                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);

        ViewCourseTime courseTime7 = ViewCourseTime.builder()
                .setTimeId("7")
                .setWeekday(3)
                .setFirstClass(5)
                .setLastClass(7)
                .setSingleOrDouble(ViewCourseTime.WEEK_ALL);

        ArrayList<ViewCourseTime> courseTimes1 = new ArrayList<>();
        courseTimes1.add(courseTime1);
        courseTimes1.add(courseTime2);

        ArrayList<ViewCourseTime> courseTimes2 = new ArrayList<>();
        courseTimes2.add(courseTime3);

        ArrayList<ViewCourseTime> courseTimes3 = new ArrayList<>();
        courseTimes3.add(courseTime4);

        ArrayList<ViewCourseTime> courseTimes4 = new ArrayList<>();
        courseTimes4.add(courseTime5);

        ArrayList<ViewCourseTime> courseTimes5 = new ArrayList<>();
        courseTimes5.add(courseTime6);

        ArrayList<ViewCourseTime> courseTimes6 = new ArrayList<>();
        courseTimes6.add(courseTime7);

        ViewCourse course1 = new ViewCourse("1","概率论1", "安楼101","李华", courseTimes1);
        ViewCourse course2 = new ViewCourse("2","概率论1", "安楼101","李华", courseTimes2);
        ViewCourse course3 = new ViewCourse("3","概率论1", "安楼101","李华", courseTimes3);
        ViewCourse course4 = new ViewCourse("4","概率论1", "安楼101","李华", courseTimes4);
        ViewCourse course5 = new ViewCourse("5","概率论1", "安楼101","李华", courseTimes5);
        ViewCourse course6 = new ViewCourse("6","概率论1", "安楼101","李华", courseTimes6);

        //Todos：取数据下来的时候，记得包含他的ObjectId（一般是默认）
        Calendar calendar = new GregorianCalendar(2000,11 - 1, 20,23,59);
        ViewTodo todo1 = new ViewTodo("todo1","交第二十一章作业", "第二十一章第1、2、5、7、12题", calendar, ViewTodo.COLOR_RED);
        ViewTodo todo2 = new ViewTodo("todo2","交第二十一章作业", "第二十一章第1、2、5、7、12题", calendar, ViewTodo.COLOR_GREEN);
        ViewTodo todo3 = new ViewTodo("todo3","交第二十一章作业", "第二十一章第1、2、5、7、12题", calendar, ViewTodo.COLOR_BLUE);

        //Table
        Map<ViewCourse, ViewTodo> courseTable = new HashMap<>();
        courseTable.put(course1, todo1);
        courseTable.put(course2, todo2);
        courseTable.put(course3, null);
        courseTable.put(course4, null);
        courseTable.put(course5, todo3);
        courseTable.put(course6, null);


        if(success) listener.onSuccess(courseTable);
        else listener.onFailure("ErrMessage");
    }

    public static void getSemesterTime(SemesterTimeListener listener){
        listener.onSuccess("第10周双周");
    }
}
