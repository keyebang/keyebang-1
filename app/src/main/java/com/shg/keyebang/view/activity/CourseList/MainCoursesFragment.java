package com.shg.keyebang.view.activity.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.presenter.courselist.MainCoursePresenter;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.CourseList.adapter.MainCourseListAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainCoursesFragment extends BaseFragment {
    private View view;
    private MainCoursePresenter presenter;
    private LRecyclerViewAdapter lMainCourseListAdapter;
    private MainCourseListAdapter mainCourseListAdapter;
    private LRecyclerView mainCourseRecyclerView;
    private LinearLayout mainCourseContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(view == null){
            presenter = new MainCoursePresenter(this);
            mainCourseListAdapter = new MainCourseListAdapter();
            lMainCourseListAdapter = new LRecyclerViewAdapter(mainCourseListAdapter);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_course_main, container, false);
        mainCourseRecyclerView = view.findViewById(R.id.mainCourseRecycler);
        mainCourseContainer = view.findViewById(R.id.mainCourseContainer);
        init();
        return view;
    }

    @Override
    protected void init() {
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this.getActivity());
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lMainCourseListAdapter.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.header_course_main, mainCourseContainer, false));
        lMainCourseListAdapter.setOnItemClickListener((view, position) -> {
            String courseName = mainCourseListAdapter.getCourseName(position);
            String courseId = mainCourseListAdapter.getCourseId(position);
            Intent intent = new Intent(getActivity(), ChooseMainCourseActivity.class);
            intent.putExtra("courseId", courseId);
            intent.putExtra("courseName", courseName);
            startActivity(intent);
        });
        mainCourseRecyclerView.setOnRefreshListener(()->presenter.getMainCourses());
        mainCourseRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mainCourseRecyclerView.setLayoutManager(verticalLayoutManager);
        mainCourseRecyclerView.setAdapter(lMainCourseListAdapter);
        mainCourseRecyclerView.setLoadMoreEnabled(false);
        presenter.getMainCourses();
    }

    public void setMainCourseData(ArrayList<ViewCourse> courses) {
        mainCourseListAdapter.setCourseList(courses);
        lMainCourseListAdapter.notifyDataSetChanged();
        mainCourseRecyclerView.refreshComplete(0);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        mainCourseRecyclerView.refreshComplete(0);
    }
}
