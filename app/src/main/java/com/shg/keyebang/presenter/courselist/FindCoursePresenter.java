package com.shg.keyebang.presenter.courselist;

import com.shg.keyebang.model.Course;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.CourseList.ChooseMainCourseActivity;
import com.shg.keyebang.view.activity.CourseList.SearchCourseActivity;

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

    public void getChooseCourseList(String courseName){
        Course course = Course.builder()
                .setClassName(courseName)
                .setTeacher("李华");
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        chooseMainCourseActivity.setChooseCourseList(courses);
    }

    public void FindCourseByName(String courseName){
        Course course = Course.builder()
                .setClassName(courseName)
                .setTeacher("李华");
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        courses.add(course);
        searchCourseActivity.setResultCourseList(courses);
    }
}
