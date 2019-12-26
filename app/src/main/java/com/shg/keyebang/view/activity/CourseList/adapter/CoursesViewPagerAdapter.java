package com.shg.keyebang.view.activity.courseList.adapter;

import com.shg.keyebang.view.activity.courseList.MainCoursesFragment;
import com.shg.keyebang.view.activity.courseList.OptionalCourseFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CoursesViewPagerAdapter extends FragmentPagerAdapter {
    private final MainCoursesFragment mainCoursesFragment;
    private final OptionalCourseFragment optionalCourseFragment;

    public CoursesViewPagerAdapter(FragmentManager manager, MainCoursesFragment mainCoursesFragment, OptionalCourseFragment optionalCourseFragment){
        super(manager);
        this.mainCoursesFragment = mainCoursesFragment;
        this.optionalCourseFragment = optionalCourseFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return (position == 0) ? mainCoursesFragment : optionalCourseFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
