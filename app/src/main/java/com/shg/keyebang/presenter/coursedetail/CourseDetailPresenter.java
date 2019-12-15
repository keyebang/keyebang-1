package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

public class CourseDetailPresenter extends BasePresenter {
    private CourseDetailActivity activity;

    public CourseDetailPresenter(CourseDetailActivity courseDetailActivity){
        this.activity = courseDetailActivity;
    }
}
