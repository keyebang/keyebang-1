package com.shg.keyebang.fakeservices.courselist;

import com.shg.keyebang.fakeservices.BaseListener;
import com.shg.keyebang.model.ViewCourse;

import java.util.ArrayList;

public interface GetCourseListListener extends BaseListener {
    void onSuccess(ArrayList<ViewCourse> courses);
}
