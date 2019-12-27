package com.shg.keyebang.presenter.courselist;

import com.shg.keyebang.services.courseList.GetCourseListListener;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.courseList.MainCourse;
import com.shg.keyebang.view.activity.courseList.MainCoursesFragment;

import java.util.ArrayList;

public class MainCoursePresenter extends BasePresenter {

    private final MainCoursesFragment fragment;

    public MainCoursePresenter(MainCoursesFragment fragment){
        this.fragment = fragment;
    }

    public void getMainCourses(){
        MainCourse.getMainCourseDa(new GetCourseListListener() {
            @Override
            public void onSuccess(ArrayList<ViewCourse> courses) {
                fragment.setMainCourseData(courses);
            }

            @Override
            public void onFailure(String errMsg) {
                fragment.showErrorMessage(errMsg);
            }
        });
    }
}
