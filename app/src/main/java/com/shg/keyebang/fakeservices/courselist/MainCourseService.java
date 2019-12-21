package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.model.ViewCourse;

import java.util.ArrayList;

public class MainCourseService {
    public static void getMainCourseData(GetCourseListListener listener) {
        ViewCourse course = ViewCourse.builder()
                .setCourseName("必修课1");
        ArrayList<ViewCourse> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        listener.onSuccess(courses);
    }
}
