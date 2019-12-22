package com.shg.keyebang.services.courseList;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.services.datamodel.Course;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
                        ViewCourse course1 = ViewCourse.builder()
                                .setCourseName(course.getClassName())
                                .setTeacher(course.getTeacher());
                        courses.add(course1);
                    }
                    listener.onSuccess(courses);
                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
            }
        });
    }
}
