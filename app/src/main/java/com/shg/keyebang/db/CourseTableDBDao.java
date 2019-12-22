package com.shg.keyebang.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.shg.keyebang.services.sqlitemodel.CourseTableDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE_TABLE_DB".
*/
public class CourseTableDBDao extends AbstractDao<CourseTableDB, Long> {

    public static final String TABLENAME = "COURSE_TABLE_DB";

    /**
     * Properties of entity CourseTableDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CourseId = new Property(0, Long.class, "courseId", true, "_id");
        public final static Property CourseName = new Property(1, String.class, "courseName", false, "COURSE_NAME");
        public final static Property Place = new Property(2, String.class, "place", false, "PLACE");
        public final static Property Teacher = new Property(3, String.class, "teacher", false, "TEACHER");
    }

    private Query<CourseTableDB> timeDB_CourseTableDBListQuery;

    public CourseTableDBDao(DaoConfig config) {
        super(config);
    }
    
    public CourseTableDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE_TABLE_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: courseId
                "\"COURSE_NAME\" TEXT," + // 1: courseName
                "\"PLACE\" TEXT," + // 2: place
                "\"TEACHER\" TEXT);"); // 3: teacher
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE_TABLE_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CourseTableDB entity) {
        stmt.clearBindings();
 
        Long courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindLong(1, courseId);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(2, courseName);
        }
 
        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(3, place);
        }
 
        String teacher = entity.getTeacher();
        if (teacher != null) {
            stmt.bindString(4, teacher);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CourseTableDB entity) {
        stmt.clearBindings();
 
        Long courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindLong(1, courseId);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(2, courseName);
        }
 
        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(3, place);
        }
 
        String teacher = entity.getTeacher();
        if (teacher != null) {
            stmt.bindString(4, teacher);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CourseTableDB readEntity(Cursor cursor, int offset) {
        CourseTableDB entity = new CourseTableDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // courseId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // courseName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // place
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // teacher
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CourseTableDB entity, int offset) {
        entity.setCourseId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCourseName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPlace(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTeacher(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CourseTableDB entity, long rowId) {
        entity.setCourseId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CourseTableDB entity) {
        if(entity != null) {
            return entity.getCourseId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CourseTableDB entity) {
        return entity.getCourseId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "courseTableDBList" to-many relationship of TimeDB. */
    public List<CourseTableDB> _queryTimeDB_CourseTableDBList(Long courseId) {
        synchronized (this) {
            if (timeDB_CourseTableDBListQuery == null) {
                QueryBuilder<CourseTableDB> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CourseId.eq(null));
                timeDB_CourseTableDBListQuery = queryBuilder.build();
            }
        }
        Query<CourseTableDB> query = timeDB_CourseTableDBListQuery.forCurrentThread();
        query.setParameter(0, courseId);
        return query.list();
    }

}
