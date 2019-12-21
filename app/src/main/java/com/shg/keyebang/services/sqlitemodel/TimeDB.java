package com.shg.keyebang.services.sqlitemodel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.shg.keyebang.db.DaoSession;
import com.shg.keyebang.db.CourseTableDBDao;
import com.shg.keyebang.db.TimeDBDao;

@Entity
public class TimeDB {
    @Id
    private Long Id;
    private int weekday;
    private int firstclass;
    private int lastclass;
    private int week;   //存储单双周，1表示单周，2表示双周，0表示全部；

    private Long courseId;
    @ToMany(referencedJoinProperty = "courseId")
    private List<CourseTableDB> courseTableDBList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 960032660)
    private transient TimeDBDao myDao;

    @Generated(hash = 1658964772)
    public TimeDB(Long Id, int weekday, int firstclass, int lastclass, int week, Long courseId) {
        this.Id = Id;
        this.weekday = weekday;
        this.firstclass = firstclass;
        this.lastclass = lastclass;
        this.week = week;
        this.courseId = courseId;
    }

    @Generated(hash = 17053194)
    public TimeDB() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) { this.Id = Id; }

    public int getWeekday() { return this.weekday; }

    public void setWeekday(int weekday) { this.weekday = weekday; }

    public int getFirstclass() { return this.firstclass; }

    public void setFirstclass(int firstclass) { this.firstclass = firstclass; }

    public int getLastclass() { return this.lastclass; }

    public void setLastclass(int lastclass) { this.lastclass = lastclass; }

    public int getWeek() { return this.week; }

    public void setWeek(int week) { this.week = week; }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 861385778)
    public List<CourseTableDB> getCourseTableDBList() {
        if (courseTableDBList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CourseTableDBDao targetDao = daoSession.getCourseTableDBDao();
            List<CourseTableDB> courseTableDBListNew = targetDao
                    ._queryTimeDB_CourseTableDBList(Id);
            synchronized (this) {
                if (courseTableDBList == null) {
                    courseTableDBList = courseTableDBListNew;
                }
            }
        }
        return courseTableDBList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1351013241)
    public synchronized void resetCourseTableDBList() {
        courseTableDBList = null;
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
    @Generated(hash = 2137407355)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTimeDBDao() : null;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
