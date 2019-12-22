package com.shg.keyebang.view.activity.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.CourseList.adapter.CoursesViewPagerAdapter;
import com.shg.keyebang.view.general.TitleBarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CourseListFragment extends BaseFragment  {
    private View view;
    private MainCoursesFragment mainCoursesFragment;
    private OptionalCourseFragment optionalCourseFragment;
    private CoursesViewPagerAdapter adapter;
    private TitleBarLayout titleBar;
    private EditText searchText;
    private ImageView search;
    private TabLayout courseListTabLayout;
    private ViewPager courseListViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(view == null){
            mainCoursesFragment = new MainCoursesFragment();
            optionalCourseFragment = new OptionalCourseFragment();
        }
        adapter = new CoursesViewPagerAdapter(getChildFragmentManager(), mainCoursesFragment, optionalCourseFragment);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) {
            initViewPager();
            return view;
        }
        view =  inflater.inflate(R.layout.fragment_course_list, container, false);
        titleBar = view.findViewById(R.id.courseListBar);
        searchText = view.findViewById(R.id.searchText);
        search = view.findViewById(R.id.searchIcon);
        courseListTabLayout = view.findViewById(R.id.courseListTabLayout);
        courseListViewPager = view.findViewById(R.id.courseListViewPager);
        init();
        return view;
    }

    @Override
    protected void init() {
        initViewPager();
        search.setOnClickListener(v -> search());
        searchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                search();
                return false;
            }
            return false;
        });
        titleBar.setTitle("您的课程列表");
    }

    private void initViewPager(){
        courseListViewPager.setAdapter(adapter);
        courseListTabLayout.setupWithViewPager(courseListViewPager);
        courseListTabLayout.getTabAt(0).setText("必修课");
        courseListTabLayout.getTabAt(1).setText("选修课");
    }

    private void search(){
        String searchValue = searchText.getText().toString();
        if(!StringUtil.isAllNullOrEmpty(searchValue)){
            Intent intent = new Intent(getActivity(), SearchCourseActivity.class);
            intent.putExtra("searchCourseName", searchText.getText().toString());
            startActivity(intent);
        }
        else toastAndLog("搜索信息为空");
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
    }
}
