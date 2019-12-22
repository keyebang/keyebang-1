package com.shg.keyebang.presenter.courselist;

import com.shg.keyebang.fakeservices.courselist.GetCourseListListener;
import com.shg.keyebang.fakeservices.courselist.MainCourseService;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.CourseList.MainCoursesFragment;

import java.util.ArrayList;

public class    MainCoursePresenter extends BasePresenter {

    private MainCoursesFragment fragment;

    public MainCoursePresenter(MainCoursesFragment fragment){
        this.fragment = fragment;
    }

    public void getMainCourses(){
        MainCourseService.getMainCourseData(new GetCourseListListener() {
            @Override
            public void onSuccess(ArrayList<ViewCourse> courses) {
                fragment.setMainCourseData(courses);
            }

            @Override
            public void onFailure(String errMsg) {

            }
        });
    }
}
