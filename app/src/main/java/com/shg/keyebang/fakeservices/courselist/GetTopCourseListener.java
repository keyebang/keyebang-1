package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.fakeservices.BaseListener;
import com.shg.keyebang.model.TopCourse;

import java.util.ArrayList;

public interface GetTopCourseListener extends BaseListener {
    void onSuccess(ArrayList<TopCourse> topCourses);
}
