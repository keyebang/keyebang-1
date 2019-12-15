package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.fakeservices.BaseListener;
import com.shg.keyebang.model.Course;

import java.util.ArrayList;

public interface GetCourseListListener extends BaseListener {
    void onSuccess(ArrayList<Course> courses);
}
