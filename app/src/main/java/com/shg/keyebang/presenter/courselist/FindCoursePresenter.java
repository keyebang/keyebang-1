package com.shg.keyebang.presenter.courselist;

import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.CourseList.ChooseMainCourseActivity;
import com.shg.keyebang.view.activity.CourseList.SearchCourseActivity;

public class FindCoursePresenter extends BasePresenter {
    private ChooseMainCourseActivity chooseMainCourseActivity;
    private SearchCourseActivity searchCourseActivity;

    public FindCoursePresenter(SearchCourseActivity searchCourseActivity) {
        this.searchCourseActivity = searchCourseActivity;
    }

    public FindCoursePresenter(ChooseMainCourseActivity activity) {
        chooseMainCourseActivity = activity;
    }

    public void getChooseCourseList(String courseName){

    }

    public void getSearchResult(String query){

    }
}
