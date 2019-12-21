package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.services.BaseListener;

import java.util.ArrayList;

public interface ThisCourseListListener extends BaseListener {
    void onSuccess(ArrayList<ViewCourseSelect> viewCourseSelects);
}
