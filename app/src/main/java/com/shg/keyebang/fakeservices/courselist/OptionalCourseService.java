package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.TopCourse;

import java.util.ArrayList;

public class OptionalCourseService {
    public static void getTopCourseData(GetTopCourseListener listener){
        TopCourse topCourse1 = new TopCourse("热门选修1");
        TopCourse topCourse2 = new TopCourse("热门选修2");
        TopCourse topCourse3 = new TopCourse("热门选修3");

        ArrayList<TopCourse> topCourses = new ArrayList<>();
        topCourses.add(topCourse1);
        topCourses.add(topCourse2);
        topCourses.add(topCourse3);

        listener.onSuccess(topCourses);
    }

    public static void getOptionalCoursesData(GetCourseListListener listener) {
        ViewCourse course1 = ViewCourse.builder()
                .setCourseName("选修课1")
                .setCampus("嘉定校区")
                .setOneOfWeekday(3)
                .setOneOfFirstClass(10)
                .setOneOfLastClass(11)
                .setCredit(1.5f);

        ArrayList<ViewCourse> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);
        courses.add(course1);

        listener.onSuccess(courses);
    }
}
