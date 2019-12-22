package com.shg.keyebang.services.courseList;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.services.BaseListener;

import java.util.ArrayList;

public interface FindCourseListener extends BaseListener {
    void onSuccess(ArrayList<ViewCourse> courses);
}
