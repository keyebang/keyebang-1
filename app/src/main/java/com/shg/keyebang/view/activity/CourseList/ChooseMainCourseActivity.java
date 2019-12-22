package com.shg.keyebang.view.activity.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.presenter.courselist.FindCoursePresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.CourseList.adapter.ItemCourseListAdapter;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;

public class ChooseMainCourseActivity extends BaseActivity {
    private String courseName;
    private FindCoursePresenter presenter;
    private TitleBarLayout chooseCourseBar;
    private LRecyclerViewAdapter lItemCourseListAdapter;
    private ItemCourseListAdapter itemCourseListAdapter;
    private LRecyclerView chooseCourseRecyclerView;
    private LinearLayout chooseCourseContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_main_choose);
        presenter = new FindCoursePresenter(this);
        itemCourseListAdapter = new ItemCourseListAdapter();
        lItemCourseListAdapter = new LRecyclerViewAdapter(itemCourseListAdapter);
        chooseCourseBar = findViewById(R.id.chooseCourseBar);
        chooseCourseContainer = findViewById(R.id.chooseCourseContainer);
        chooseCourseRecyclerView = findViewById(R.id.chooseCourseRecycler);
        init();
    }

    @Override
    protected void init() {
        courseName = getIntent().getStringExtra("courseName" );
        chooseCourseBar.setTitle(courseName);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lItemCourseListAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_course_item, chooseCourseContainer, false));
        lItemCourseListAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CourseDetailActivity.class);
            intent.putExtra("courseId", itemCourseListAdapter.getCourseId(position));
            intent.putExtra("courseName", itemCourseListAdapter.getCourseName(position));
            intent.putExtra("courseTeacher", itemCourseListAdapter.getCourseTeacher(position));
            startActivity(intent);
        });
        chooseCourseRecyclerView.setOnRefreshListener(()->presenter.getChooseCourseList(courseName));
        chooseCourseRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        chooseCourseRecyclerView.setLayoutManager(verticalLayoutManager);
        chooseCourseRecyclerView.setAdapter(lItemCourseListAdapter);
        chooseCourseRecyclerView.setLoadMoreEnabled(false);

        presenter.getChooseCourseList(courseName);
    }

    public void setChooseCourseList(ArrayList<ViewCourse> courses) {
        itemCourseListAdapter.setCourseList(courses);
        lItemCourseListAdapter.notifyDataSetChanged();
        chooseCourseRecyclerView.refreshComplete(0);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        chooseCourseRecyclerView.refreshComplete(0);
    }
}
