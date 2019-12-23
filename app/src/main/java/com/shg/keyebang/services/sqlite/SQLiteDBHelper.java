package com.shg.keyebang.services.sqlite;

import com.shg.keyebang.db.DaoMaster;
import com.shg.keyebang.db.DaoSession;
import com.shg.keyebang.db.CourseTableDBDao;
import com.shg.keyebang.db.TimeDBDao;
import com.shg.keyebang.db.TodoDBDao;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.presenter.coursetable.CourseTablePresenter;
import com.shg.keyebang.services.coursetable.CourseTable;
import com.shg.keyebang.services.coursetable.CourseTableListener;

import com.shg.keyebang.services.coursetable.GetClassListener;

import com.shg.keyebang.services.datamodel.Course;
import com.shg.keyebang.services.datamodel.CourseTime;
import com.shg.keyebang.services.datamodel.Todo;
import com.shg.keyebang.services.sqlitemodel.CourseTableDB;
import com.shg.keyebang.services.sqlitemodel.TimeDB;
import com.shg.keyebang.services.sqlitemodel.TodoDB;


import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class SQLiteDBHelper {
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private CourseTableDBDao courseTableDBDao;
    private TimeDBDao timeDBDao;
    private TodoDBDao todoDBDao;

    private static SQLiteDBHelper mCourseTableDBHelper;

    /**
     * 初始化
     */
    public SQLiteDBHelper(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context,"coursetable.db", null);
        mDaoMaster =new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        courseTableDBDao = mDaoSession.getCourseTableDBDao();
        timeDBDao = mDaoSession.getTimeDBDao();
        todoDBDao = mDaoSession.getTodoDBDao();
    }

    /**
     * 获取可读数据库
     */
    public void getReadableDB(){
        if(mHelper == null){
            mHelper = new DaoMaster.DevOpenHelper(context,"coursetable.db",null);
        }
        SQLiteDatabase db =mHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase(){
        if(mHelper == null){
            mHelper =new DaoMaster.DevOpenHelper(context,"coursetable.db",null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    /**
     * 添加一条课程数据
     */
    public void insertCourse(Course course,List<CourseTime> time) {
        courseTableDBDao = mDaoSession.getCourseTableDBDao();
        timeDBDao = mDaoSession.getTimeDBDao();
        long courseId = Long.parseLong(course.getObjectId());
        CourseTableDB courseTableDB= new CourseTableDB(courseId,course.getClassName(),course.getClassPlace(),course.getTeacher());
        courseTableDBDao.insertOrReplace(courseTableDB);
        for (CourseTime courseTime:time){
            long timeId = Long.parseLong(courseTime.getObjectId());
            TimeDB timeDB = new TimeDB(timeId,courseTime.getWeekday(),courseTime.getFirstClass(),courseTime.getLastClass(),courseTime.getWeekTime(),courseId);
            timeDBDao.insertOrReplace(timeDB);
        }
    }

    /**
     * 删除一条课程数据
     */
    public void deleteCourse(Long courseId){
        Long mCourseId;
        mCourseId = courseId;
        courseTableDBDao = mDaoSession.getCourseTableDBDao();
        timeDBDao = mDaoSession.getTimeDBDao();
        courseTableDBDao.deleteByKey(mCourseId);
        timeDBDao.deleteByKey(mCourseId);
    }

    /**
     * 删除所有
     */
    private SQLiteDatabase deleteAll(){
        courseTableDBDao = mDaoSession.getCourseTableDBDao();
        courseTableDBDao.deleteAll();
        timeDBDao.deleteAll();
        return db;
    }

    /**
     * 添加一条Todo数据
     */
    /*private SQLiteDatabase insertTodo(Todo todo) {
        long todoId = Long.parseLong(todo.getObjectId());
        Course todoCourse = todo.getCourseId();
        long courseId = Long.parseLong(todoCourse.getObjectId());
        todoDBDao = mDaoSession.getTodoDBDao();
        TodoDB todoDB=new TodoDB(todoId,todo.getTodoTitle(),todo.getYear(),todo.getMonth(),todo.getDayOfMonth(),todo.getTodoMessage(),todo.getColor(),courseId);
        todoDBDao.insertOrReplace(todoDB);
        return db;
    }


    /**
     * 删除一条Todo数据
     */
    private SQLiteDatabase deleteTodo(Long todoId){
        Long mTodoId = todoId;
        todoDBDao = mDaoSession.getTodoDBDao();
        todoDBDao.deleteByKey(mTodoId);
        return db;
    }

    /**
     * 更新一条Todo数据
     */
    private SQLiteDatabase updateTodo(TodoDB todoDB){
        todoDBDao = mDaoSession.getTodoDBDao();
        todoDBDao.update(todoDB);
        return db;
    }

    /**
     * 查询所有数据
     */
    public List<CourseTableDB> searchAll(){
        List<CourseTableDB> courseTableDBS =courseTableDBDao.queryBuilder().list();
        return courseTableDBS;
    }

    private void showCourseTable(GetClassListener listener){
        Map<ViewCourse,ViewTodo> classTable = new HashMap<>();
        List<CourseTableDB> courseTableDBS =courseTableDBDao.queryBuilder().list();
        for (CourseTableDB courseTableDB1:courseTableDBS){
            Long courseId1 =courseTableDB1.getCourseId();
            List<TimeDB> timeDBS = (List<TimeDB>) timeDBDao.queryBuilder().where(TimeDBDao.Properties.CourseId.eq(courseId1)).build().unique();
            ArrayList<ViewCourseTime> courseTimes = new ArrayList<>();
            for (TimeDB timeDB1:timeDBS){
                String timeId = Long.toString(timeDB1.getId());
                ViewCourseTime courseTime = ViewCourseTime.builder()
                        .setTimeId(timeId)
                        .setWeekday(timeDB1.getWeekday())
                        .setFirstClass(timeDB1.getFirstclass())
                        .setLastClass(timeDB1.getLastclass())
                        .setSingleOrDouble(timeDB1.getWeek());
                courseTimes.add(courseTime);
            }
            String courseId = Long.toString(courseTableDB1.getCourseId());
            ViewCourse viewCourse = new ViewCourse(courseId,courseTableDB1.getCourseName(),courseTableDB1.getPlace(),courseTableDB1.getTeacher(),courseTimes);
            List<TodoDB> todoDBS = (List<TodoDB>) todoDBDao.queryBuilder().where(TodoDBDao.Properties.CourseId.eq(courseId1)).build().unique();
            for (TodoDB todoDB : todoDBS){
                String todoId = Long.toString(todoDB.getTodoId());
                Calendar calendar = new GregorianCalendar(todoDB.getDeadlineYear(),todoDB.getDeadlineMonth(),todoDB.getDeadlineDay());
                ViewTodo viewTodo = new ViewTodo(todoId,todoDB.getTodoTitle(),todoDB.getTodoMessage(),calendar,todoDB.getColor());
                classTable.put(viewCourse, viewTodo);
            }

        }
        listener.onSuccess(classTable);

    }

}
