package com.shg.keyebang.services.sqlitemodel;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class CourseTableDB {
    @Id
    private Long courseId;
    private String courseName;
    private String place;
    private String teacher;


    @Generated(hash = 586380683)
    public CourseTableDB(Long courseId, String courseName, String place,
            String teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.place = place;
        this.teacher = teacher;
    }


    @Generated(hash = 1957824133)
    public CourseTableDB() {
    }


    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
