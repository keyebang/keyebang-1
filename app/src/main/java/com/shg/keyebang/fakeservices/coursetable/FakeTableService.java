package com.shg.keyebang.fakeservices.coursetable;

import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.view.activity.coursetable.CourseCard;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class FakeTableService {
    public static void getTable(String studentId, FakeGetTableListener listener){

        boolean success = true;

        //Course: 取数据下来的时候，记得包含他的ObjectId（一般是默认）
        Course course1 = new Course("概率论1", "安楼101","李华",1,1,2);
        Course course2 = new Course("概率论2", "安楼101","李华",1,3,4);
        Course course3 = new Course("概率论3", "安楼101","李华",2,5,8);
        Course course4 = new Course("概率论4", "安楼101","李华",3,1,2);
        Course course5 = new Course("概率论概率论概率论", "安楼101","李华",5,1,2);
        Course course6 = new Course("概率论6", "安楼101","李华",4,3,4);
        Course course7 = new Course("概率论概率论概率论", "安楼101","李华",2,3,4);
        Course course8 = new Course("组合数学", "安楼101","李华",2,1,2);
        Course course9 = new Course("组合数学", "安楼101","李华",4,1,2);
        Course course10 = new Course("组合数学", "安楼101","李华",3,5,6);
        Course course11 = new Course("组合数学", "安楼101","李华",4,5,6);

        //Todos：取数据下来的时候，记得包含他的ObjectId（一般是默认）
        Calendar calendar = new GregorianCalendar(2000,11 - 1, 20,23,59);
        Todo todo1 = new Todo("交第二十一章作业", "第二十一章第1、2、5、7、12题", calendar, Todo.COLOR_RED);
        Todo todo2 = new Todo("交第二十一章作业", "第二十一章第1、2、5、7、12题", calendar, Todo.COLOR_GREEN);
        Todo todo3 = new Todo("交第二十一章作业", "第二十一章第1、2、5、7、12题", calendar, Todo.COLOR_BLUE);

        //Table
        Map<Course, Todo> classTable = new HashMap<>();
        classTable.put(course1, null);
        classTable.put(course2, null);
        classTable.put(course3, todo1);
        classTable.put(course7, null);
        classTable.put(course4, todo2);
        classTable.put(course5, null);
        classTable.put(course6, null);
        classTable.put(course8, todo3);
        classTable.put(course9, null);
        classTable.put(course10, null);
        classTable.put(course11, null);


        if(success) listener.onSuccess(classTable);
        else listener.onFailure("ErrMessage");
    }

    public static void getSemesterTime(SemesterTimeListener listener){
        listener.onSuccess("第10周双周");
    }
}
