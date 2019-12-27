package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.MyApplication;
import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewTopCourse;

import java.util.ArrayList;

public class OptionalCourseService {
    public static void getTopCourseData(GetTopCourseListener listener){
        ViewTopCourse topCourse1 = new ViewTopCourse("绘画实践");
        topCourse1.setCourseId("V0uWqHHq");
        topCourse1.setImg(MyApplication.getContext().getResources().getDrawable(R.drawable.draw, null));
        ViewTopCourse topCourse2 = new ViewTopCourse("星期音乐会");
        topCourse2.setCourseId("Mb7saaao");
        topCourse2.setImg(MyApplication.getContext().getResources().getDrawable(R.drawable.music, null));
        ViewTopCourse topCourse3 = new ViewTopCourse("数码摄影");
        topCourse3.setCourseId("rpiLFFFd");
        topCourse3.setImg(MyApplication.getContext().getResources().getDrawable(R.drawable.photo, null));

        ArrayList<ViewTopCourse> topCourses = new ArrayList<>();
        topCourses.add(topCourse1);
        topCourses.add(topCourse2);
        topCourses.add(topCourse3);

        listener.onSuccess(topCourses);
    }
}
