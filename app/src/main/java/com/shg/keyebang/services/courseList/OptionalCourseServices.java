package com.shg.keyebang.services.courseList;

import com.shg.keyebang.fakeservices.courselist.GetCourseListListener;
import com.shg.keyebang.fakeservices.courselist.GetTopCourseListener;
import com.shg.keyebang.model.TopCourse;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.services.datamodel.Course;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OptionalCourseServices {
    public static void getTopCourse(GetTopCourseListener listener){
        BmobQuery<Course> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("type","optional");
        query1.include("evaluationId[likes]");
        query1.order("likes");
        query1.setLimit(3);
        query1.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> object, BmobException e){
                if(e==null){
                    ArrayList<TopCourse> topCourses = new ArrayList<>();
                    for(Course course:object){
                        TopCourse topCourse1 = new TopCourse(course.getClassName());

                        topCourses.add(topCourse1);
                    }
                    listener.onSuccess(topCourses);
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }


        });
    }

    public static void getOptionalCourses(GetCourseListListener listener){

        BmobQuery<Course> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("type","optional");
        query1.setLimit(100);
        query1.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> object, BmobException e){
                if(e==null){
                    ArrayList<ViewCourse> courses = new ArrayList<>();
                    for(Course course:object){
                        ViewCourse course1 = ViewCourse.builder()
                                .setCourseName(course.getClassName())
                                .setCampus(course.getCampus())
                                .setCredit(course.getCredit());
                        courses.add(course1);
                    }
                    listener.onSuccess(courses);
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }


        });
    }
}
