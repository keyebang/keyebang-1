package com.shg.keyebang.services.sqlite;

import com.shg.keyebang.db.DaoMaster;
import com.shg.keyebang.db.DaoSession;
import com.shg.keyebang.db.CourseTableDBDao;
import com.shg.keyebang.db.TimeDBDao;
import com.shg.keyebang.db.TodoDBDao;

import com.shg.keyebang.services.coursetable.CourseTable;
import com.shg.keyebang.services.coursetable.CourseTableListener;

import com.shg.keyebang.services.coursetable.GetClassListener;
import com.shg.keyebang.services.sqlitemodel.CourseTableDB;
import com.shg.keyebang.services.sqlitemodel.TimeDB;
import com.shg.keyebang.services.sqlitemodel.TodoDB;


import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
    private SQLiteDatabase getReadableDatabase(){
        if(mHelper == null){
            mHelper = new DaoMaster.DevOpenHelper(context,"coursetable.db",null);
        }
        SQLiteDatabase db =mHelper.getReadableDatabase();
        return db;
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
    private SQLiteDatabase insertCourse(CourseTableDB courseTable,List<TimeDB> time) {
        courseTableDBDao = mDaoSession.getCourseTableDBDao();
        timeDBDao = mDaoSession.getTimeDBDao();
        courseTableDBDao.insertOrReplace(courseTable);
        for (TimeDB timeDB:time){
            timeDBDao.insertOrReplace(timeDB);
        }
        return db;
    }

    /**
     * 删除一条课程数据
     */
    private SQLiteDatabase deleteCourse(Long courseId){
        Long mCourseId;
        mCourseId = courseId;
        courseTableDBDao = mDaoSession.getCourseTableDBDao();
        timeDBDao = mDaoSession.getTimeDBDao();
        courseTableDBDao.deleteByKey(mCourseId);
        timeDBDao.deleteByKey(mCourseId);
        return db;
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
    private SQLiteDatabase insertTodo(TodoDB todoDB) {
        todoDBDao = mDaoSession.getTodoDBDao();
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
        Map<CourseTableDB,List<TimeDB>> classTable = new HashMap<>();
        Map<CourseTableDB,TodoDB> todoTable = new HashMap<>();
        List<CourseTableDB> courseTableDBS =courseTableDBDao.queryBuilder().list();
        for (CourseTableDB courseTableDB1 : courseTableDBS){
            CourseTableDB courseTableDB2 = new CourseTableDB(courseTableDB1.getCourseId(),courseTableDB1.getCourseName(),courseTableDB1.getPlace(),courseTableDB1.getTeacher());
            Long courseId1 =courseTableDB2.getCourseId();
            List<TimeDB> timeDBS = (List<TimeDB>) timeDBDao.queryBuilder().where(TimeDBDao.Properties.CourseId.eq(courseId1)).build().unique();
            List<TodoDB> todoDBS = (List<TodoDB>) todoDBDao.queryBuilder().where(TodoDBDao.Properties.CourseId.eq(courseId1)).build().unique();
            for (TodoDB todoDB1:todoDBS){
                todoTable.put(courseTableDB2,todoDB1);
            }
            classTable.put(courseTableDB2,timeDBS);
        }
    }

}
