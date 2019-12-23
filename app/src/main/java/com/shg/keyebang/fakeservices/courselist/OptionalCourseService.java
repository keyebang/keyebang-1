package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.MyApplication;
import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.TopCourse;

import java.util.ArrayList;

public class OptionalCourseService {
    public static void getTopCourseData(GetTopCourseListener listener){
        TopCourse topCourse1 = new TopCourse("绘画实践");
        topCourse1.setCourseId("V0uWqHHq");
        topCourse1.setImg(MyApplication.getContext().getResources().getDrawable(R.drawable.draw, null));
        TopCourse topCourse2 = new TopCourse("星期音乐会");
        topCourse2.setCourseId("Mb7saaao");
        topCourse2.setImg(MyApplication.getContext().getResources().getDrawable(R.drawable.music, null));
        TopCourse topCourse3 = new TopCourse("数码摄影");
        topCourse3.setCourseId("rpiLFFFd");
        topCourse3.setImg(MyApplication.getContext().getResources().getDrawable(R.drawable.photo, null));

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
