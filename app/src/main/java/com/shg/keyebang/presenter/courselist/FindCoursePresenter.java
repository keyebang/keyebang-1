package com.shg.keyebang.presenter.courselist;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.courseList.FindCourseListener;
import com.shg.keyebang.services.courseList.FindCourseService;
import com.shg.keyebang.view.activity.courseList.ChooseMainCourseActivity;
import com.shg.keyebang.view.activity.courseList.SearchCourseActivity;

import java.util.ArrayList;

public class FindCoursePresenter extends BasePresenter {
    private ChooseMainCourseActivity chooseMainCourseActivity;
    private SearchCourseActivity searchCourseActivity;

    public FindCoursePresenter(SearchCourseActivity searchCourseActivity) {
        this.searchCourseActivity = searchCourseActivity;
    }

    public FindCoursePresenter(ChooseMainCourseActivity activity) {
        chooseMainCourseActivity = activity;
    }

    public void getChooseMainCourseList(String courseName){
        FindCourseService.getChooseCourseList(courseName, new FindCourseListener() {
            @Override
            public void onSuccess(ArrayList<ViewCourse> courses) {
                chooseMainCourseActivity.setChooseCourseList(courses);
            }

            @Override
            public void onFailure(String errMassage) {
                chooseMainCourseActivity.showErrorMessage(errMassage);
            }
        });
    }

    public void FindCourseByName(String courseName){
        FindCourseService.getChooseCourseList(courseName, new FindCourseListener() {
            @Override
            public void onSuccess(ArrayList<ViewCourse> courses) {
                searchCourseActivity.setResultCourseList(courses);
            }

            @Override
            public void onFailure(String errMassage) {
                searchCourseActivity.showErrorMessage(errMassage);
            }
        });
    }
}
