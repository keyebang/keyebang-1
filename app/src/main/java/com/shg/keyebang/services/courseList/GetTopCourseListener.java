package com.shg.keyebang.services.courseList;


import com.shg.keyebang.model.ViewTopCourse;
import com.shg.keyebang.services.BaseListener;

import java.util.ArrayList;

public interface GetTopCourseListener extends BaseListener {
    void onSuccess(ArrayList<ViewTopCourse> topCourses);
}
