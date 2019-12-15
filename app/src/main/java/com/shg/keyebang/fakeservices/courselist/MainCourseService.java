package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.model.Course;

import java.util.ArrayList;

public class MainCourseService {
    public static void getMainCourseData(GetCourseListListener listener) {
        Course course = Course.builder()
                .setClassName("必修课1");
        ArrayList<Course> courses = new ArrayList<>();
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
