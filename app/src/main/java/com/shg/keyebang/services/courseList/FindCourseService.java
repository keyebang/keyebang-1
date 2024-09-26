package com.shg.keyebang.services.courseList;

import android.util.Log;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.services.datamodel.Course;
import com.shg.keyebang.services.datamodel.CourseTime;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FindCourseService {
    public static void getChooseCourseList(String courseName,FindCourseListener listener){
        BmobQuery<Course> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("className",courseName);
        query1.setLimit(100);
        query1.findObjects(new FindListener<Course>(){
            @Override
            public void done(List<Course> object, BmobException e){
                if(e==null){
                    ArrayList<ViewCourse> courses = new ArrayList<>();
                    for(Course course:object){
                        BmobQuery<CourseTime> query2 = new BmobQuery<>();
                        query2.addWhereEqualTo("courseId",course.getObjectId());
                        query2.findObjects(new FindListener<CourseTime>() {
                            @Override
                            public void done(List<CourseTime> object, BmobException e) {
                                if(e==null){
                                    ArrayList<ViewCourseTime> times=new ArrayList<>();
                                    for (CourseTime courseTime:object){
                                        ViewCourseTime time1 =ViewCourseTime.builder()
                                                .setWeekday(courseTime.getWeekday())
                                                .setSingleOrDouble(courseTime.getWeekTime())
                                                .setFirstClass(courseTime.getFirstClass())
                                                .setLastClass(courseTime.getLastClass())
                                                .setTimeId(courseTime.getObjectId());
                                        times.add(time1);

                                    }
                                    ViewCourse course1 = ViewCourse.builder()
                                            .setCourseName(course.getClassName())
                                            .setTeacher(course.getTeacher())
                                            .setCourseId(course.getObjectId())
                                            .setCourseTimes(times);
                                    courses.add(course1);
                                    listener.onSuccess(courses);
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                            }
                        });
                    }
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }
        });
    }
}
