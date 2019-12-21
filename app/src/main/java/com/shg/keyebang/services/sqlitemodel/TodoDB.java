package com.shg.keyebang.services.sqlitemodel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import com.shg.keyebang.db.DaoSession;
import org.greenrobot.greendao.DaoException;
import com.shg.keyebang.db.CourseTableDBDao;
import com.shg.keyebang.db.TodoDBDao;

@Entity
public class TodoDB {
    @Id
    private Long todoId;
    private String todoTitle;
    private int deadlineYear;
    private int deadlineMonth;
    private int deadlineDay;
    private String todoMessage;
    private int color;

    private Long courseId;
    @ToOne(joinProperty = "courseId")
    private CourseTableDB courseTableDB;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1100348644)
    private transient TodoDBDao myDao;
    @Generated(hash = 2105947424)
    private transient Long courseTableDB__resolvedKey;

    @Generated(hash = 612100448)
    public TodoDB(Long todoId, String todoTitle, int deadlineYear, int deadlineMonth, int deadlineDay,
            String todoMessage, int color, Long courseId) {
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.deadlineYear = deadlineYear;
        this.deadlineMonth = deadlineMonth;
        this.deadlineDay = deadlineDay;
        this.todoMessage = todoMessage;
        this.color = color;
        this.courseId = courseId;
    }

    @Generated(hash = 1395539565)
    public TodoDB() {
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoMessage() { return todoMessage; }

    public void setTodoMessage(String todoMessage) {
        this.todoMessage = todoMessage;
    }

    public int getDeadlineYear() {
        return deadlineYear;
    }

    public void setDeadlineYear(int deadlineYear) {
        this.deadlineYear = deadlineYear;
    }

    public int getDeadlineMonth() {
        return deadlineMonth;
    }

    public void setDeadlineMonth(int deadlineMonth) {
        this.deadlineMonth = deadlineMonth;
    }

    public int getDeadlineDay() {
        return deadlineDay;
    }

    public void setDeadlineDay(int deadlineDay) {
        this.deadlineDay = deadlineDay;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1815830117)
    public CourseTableDB getCourseTableDB() {
        Long __key = this.courseId;
        if (courseTableDB__resolvedKey == null || !courseTableDB__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CourseTableDBDao targetDao = daoSession.getCourseTableDBDao();
            CourseTableDB courseTableDBNew = targetDao.load(__key);
            synchronized (this) {
                courseTableDB = courseTableDBNew;
                courseTableDB__resolvedKey = __key;
            }
        }
        return courseTableDB;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1264338197)
    public void setCourseTableDB(CourseTableDB courseTableDB) {
        synchronized (this) {
            this.courseTableDB = courseTableDB;
            courseId = courseTableDB == null ? null : courseTableDB.getCourseId();
            courseTableDB__resolvedKey = courseId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1226409196)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTodoDBDao() : null;
    }

}
