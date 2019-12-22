package com.shg.keyebang.view.activity.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.TopCourse;
import com.shg.keyebang.presenter.courselist.OptionalCoursePresenter;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.CourseList.adapter.OptionalCourseListAdapter;
import com.shg.keyebang.view.activity.CourseList.adapter.TopCourseListAdapter;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OptionalCourseFragment extends BaseFragment {
    private View view;
    private OptionalCoursePresenter presenter;
    private RecyclerView topCourseRecyclerView;
    private TopCourseListAdapter topCourseListAdapter;
    private LRecyclerView optionalCourseRecyclerView;
    private OptionalCourseListAdapter optionalCourseListAdapter;
    private LRecyclerViewAdapter lOptionalCourseListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(view == null){
            presenter = new OptionalCoursePresenter(this);
            topCourseListAdapter = new TopCourseListAdapter(this);
            optionalCourseListAdapter = new OptionalCourseListAdapter();
            lOptionalCourseListAdapter = new LRecyclerViewAdapter(optionalCourseListAdapter);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_course_optional, container, false);
        topCourseRecyclerView = view.findViewById(R.id.topCourseRecycler);
        optionalCourseRecyclerView = view.findViewById(R.id.optionalCourseRecycler);
        init();
        return view;
    }

    @Override
    protected void init() {
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this.getActivity());
        horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topCourseRecyclerView.setLayoutManager(horizontalLayoutManager);
        topCourseRecyclerView.setAdapter(topCourseListAdapter);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this.getActivity());
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lOptionalCourseListAdapter.setOnItemClickListener((v, p)->{
            Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
            intent.putExtra("courseId", optionalCourseListAdapter.getCourseId(p));
            intent.putExtra("courseName", optionalCourseListAdapter.getCourseName(p));
            intent.putExtra("courseTeacher", optionalCourseListAdapter.getCourseTeacher(p));
            startActivity(intent);
        });
        optionalCourseRecyclerView.setLayoutManager(verticalLayoutManager);
        optionalCourseRecyclerView.setAdapter(lOptionalCourseListAdapter);
        optionalCourseRecyclerView.setOnRefreshListener(()->presenter.getOptionalCourses());
        optionalCourseRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        presenter.getTopCourses();
        presenter.getOptionalCourses();
    }

    public void setTopCoursesData(ArrayList<TopCourse> topCourses){
        topCourseListAdapter.setTopCourses(topCourses);
        topCourseListAdapter.notifyDataSetChanged();
    }

    public void setOptionalCourseData(ArrayList<ViewCourse> courses) {
        optionalCourseListAdapter.setCourseList(courses);
        lOptionalCourseListAdapter.notifyDataSetChanged();
        optionalCourseRecyclerView.refreshComplete(0);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        optionalCourseRecyclerView.refreshComplete(0);
    }
}
