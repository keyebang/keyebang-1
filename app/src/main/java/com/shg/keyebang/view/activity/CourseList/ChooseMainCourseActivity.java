package com.shg.keyebang.view.activity.CourseList;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.shg.keyebang.R;
import com.shg.keyebang.presenter.courselist.FindCoursePresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.CourseList.adapter.ChooseCourseListAdapter;
import com.shg.keyebang.view.general.TitleBarLayout;

public class ChooseMainCourseActivity extends BaseActivity {
    private String courseName;
    private FindCoursePresenter presenter;
    private TitleBarLayout chooseCourseBar;
    private LRecyclerViewAdapter lChooseCourseListAdapter;
    private ChooseCourseListAdapter chooseCourseListAdapter;
    private LRecyclerView chooseCourseRecyclerView;
    private LinearLayout chooseCourseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_main_choose);
        presenter = new FindCoursePresenter(this);
        chooseCourseListAdapter = new ChooseCourseListAdapter();
        lChooseCourseListAdapter = new LRecyclerViewAdapter(chooseCourseListAdapter);
        chooseCourseBar = findViewById(R.id.chooseCourseBar);
        chooseCourseContainer = findViewById(R.id.chooseCourseContainer);
        chooseCourseRecyclerView = findViewById(R.id.chooseCourseRecycler);
        init();
    }

    @Override
    protected void init() {
        courseName = getIntent().getStringExtra("courseName" );
        chooseCourseBar.setTitle(courseName);
        presenter.getChooseCourseList(courseName);
    }

}
