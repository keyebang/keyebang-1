package com.shg.keyebang.presenter.courselist;

import com.shg.keyebang.fakeservices.courselist.GetCourseListListener;
import com.shg.keyebang.fakeservices.courselist.GetTopCourseListener;
import com.shg.keyebang.fakeservices.courselist.OptionalCourseService;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.TopCourse;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.CourseList.OptionalCourseFragment;

import java.util.ArrayList;

public class OptionalCoursePresenter extends BasePresenter {
    private OptionalCourseFragment fragment;

    public OptionalCoursePresenter(OptionalCourseFragment fragment){
        this.fragment = fragment;
    }

    public void getTopCourses(){
        OptionalCourseService.getTopCourseData(new GetTopCourseListener() {
            @Override
            public void onSuccess(ArrayList<TopCourse> topCourses) {
                fragment.setTopCoursesData(topCourses);
            }

            @Override
            public void onFailure(String errMsg) {

            }
        });
    }

    public void getOptionalCourses(){
        OptionalCourseService.getOptionalCoursesData(new GetCourseListListener() {
            @Override
            public void onSuccess(ArrayList<ViewCourse> courses) {
                fragment.setOptionalCourseData(courses);
            }

            @Override
            public void onFailure(String errMsg) {

            }
        });
    }

}
