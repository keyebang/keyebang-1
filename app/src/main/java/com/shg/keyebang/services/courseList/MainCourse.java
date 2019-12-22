package com.shg.keyebang.services.courseList;

import com.shg.keyebang.fakeservices.courselist.GetCourseListListener;
import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.services.datamodel.Course;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainCourse {
    public static void getMainCourseDa(GetCourseListListener listener){
        BmobQuery<User> query=new BmobQuery<>();
        query.addWhereEqualTo("objectId", BmobUser.getCurrentUser(User.class));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if(e==null){
                    for(User user:object){
                        BmobQuery<Course> query1 = new BmobQuery<>();
                        query1.addWhereEqualTo("type","必修课");
                        query1.addWhereEqualTo("semester",user.getSemester());
                        query1.setLimit(100);
                        query1.findObjects(new FindListener<Course>() {
                            @Override
                            public void done(List<Course> object, BmobException e){
                                if(e==null){
                                    ArrayList<ViewCourse> courses = new ArrayList<>();
                                    for(Course course:object){
                                        String name = course.getClassName();
                                        int find = 0;
                                        for(ViewCourse viewCourse: courses){
                                            if(viewCourse.getCourseName().equals(name)) find = 1;
                                        }
                                        if(find == 1) continue;
                                        ViewCourse course1 = ViewCourse.builder()
                                                .setCourseName(course.getClassName());
                                        courses.add(course1);
                                    }
                                    listener.onSuccess(courses);
                                }else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}
                            }


                        });
                    }
                }
            }
        });
    }
}
