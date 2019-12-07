package com.shg.keyebang.view.activity.CourseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.view.activity.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

public class CourseListFragment extends BaseFragment  {
    private MainCoursesFragment mainCoursesFragment;
    private OptionalCourseFragment optionalCourseFragment;
    private CoursesViewPagerAdapter adapter;
    private ImageView avatar;
    private EditText searchText;
    private ImageView search;
    private TabLayout courseListTabLayout;
    private ViewPager coursesViewPager;

    @Override
    protected void init(View view) {
        avatar = view.findViewById(R.id.avatar);
        searchText = view.findViewById(R.id.searchText);
        search = view.findViewById(R.id.searchIcon);
        courseListTabLayout = view.findViewById(R.id.courseListTabLayout);
        coursesViewPager = view.findViewById(R.id.courseListViewPager);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainCoursesFragment = new MainCoursesFragment();
        optionalCourseFragment = new OptionalCourseFragment();
        adapter = new CoursesViewPagerAdapter(getChildFragmentManager(), mainCoursesFragment, optionalCourseFragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_courselist, container, false);
        init(view);

        coursesViewPager.setAdapter(adapter);
        courseListTabLayout.setupWithViewPager(coursesViewPager);
        courseListTabLayout.getTabAt(0).setText("必修课");
        courseListTabLayout.getTabAt(1).setText("选修课");

        search.setOnClickListener(v -> toastAndLog("Search"));
        avatar.setOnClickListener(v->{
            User user = User.getCurrentUser(User.class);
            if(user != null) {
                toastAndLog(
                        "当前用户：" + "\n" +
                                user.getUsername() + "\n" +
                                user.getNickname() + "\n" +
                                user.getStudentId());
            }
        });

        return view;
    }
}
