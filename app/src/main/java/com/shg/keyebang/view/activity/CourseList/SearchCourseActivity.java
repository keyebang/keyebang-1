package com.shg.keyebang.view.activity.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.presenter.courselist.FindCoursePresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.CourseList.adapter.ItemCourseListAdapter;
import com.shg.keyebang.view.activity.coursedetail.CourseDetailActivity;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;

public class SearchCourseActivity extends BaseActivity {
    private String searchCourseName;
    private FindCoursePresenter presenter;
    private ItemCourseListAdapter itemCourseListAdapter;
    private LRecyclerViewAdapter lItemCourseListAdapter;
    private LRecyclerView searchCourseRecyclerView;
    private LinearLayout searchContainer;
    private TitleBarLayout searchTitleBar;
    private EditText searchText;
    private ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
        presenter = new FindCoursePresenter(this);
        itemCourseListAdapter = new ItemCourseListAdapter();
        lItemCourseListAdapter = new LRecyclerViewAdapter(itemCourseListAdapter);
        searchCourseRecyclerView = findViewById(R.id.searchCourseRecycler);
        searchContainer = findViewById(R.id.searchContainer);
        searchTitleBar = findViewById(R.id.searchTitleBar);
        searchText = findViewById(R.id.searchText);
        searchIcon = findViewById(R.id.searchIcon);
        init();
    }

    @Override
    protected void init() {
        searchCourseName = getIntent().getStringExtra("searchCourseName" );
        searchTitleBar.setTitle("搜索你的课程");
        searchText.setText(searchCourseName);
        searchIcon.setOnClickListener(v -> search());
        searchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                search();
                return false;
            }
            return false;
        });
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lItemCourseListAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_course_item, searchContainer, false));
        lItemCourseListAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CourseDetailActivity.class);
            intent.putExtra("courseId", itemCourseListAdapter.getCourseId(position));
            intent.putExtra("courseName", itemCourseListAdapter.getCourseName(position));
            intent.putExtra("courseTeacher", itemCourseListAdapter.getCourseTeacher(position));
            startActivity(intent);
        });
        searchCourseRecyclerView.setOnRefreshListener(()->presenter.FindCourseByName(searchCourseName));
        searchCourseRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        searchCourseRecyclerView.setLayoutManager(verticalLayoutManager);
        searchCourseRecyclerView.setAdapter(lItemCourseListAdapter);
        searchCourseRecyclerView.setLoadMoreEnabled(false);

        presenter.FindCourseByName(searchCourseName);
    }

    private void search(){
        searchCourseName = searchText.getText().toString();
        if(!StringUtil.isAllNullOrEmpty(searchCourseName)){
            presenter.FindCourseByName(searchCourseName);
        }
        else {
            toastAndLog("搜索信息为空");
            searchCourseRecyclerView.refreshComplete(0);
        }
    }

    public void setResultCourseList(ArrayList<ViewCourse> courses) {
        itemCourseListAdapter.setCourseList(courses);
        lItemCourseListAdapter.notifyDataSetChanged();
        searchCourseRecyclerView.refreshComplete(0);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        searchCourseRecyclerView.refreshComplete(0);
    }
}
