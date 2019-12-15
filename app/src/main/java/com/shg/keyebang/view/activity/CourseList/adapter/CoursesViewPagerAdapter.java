package com.shg.keyebang.view.activity.CourseList.adapter;

import com.shg.keyebang.view.activity.CourseList.MainCoursesFragment;
import com.shg.keyebang.view.activity.CourseList.OptionalCourseFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CoursesViewPagerAdapter extends FragmentPagerAdapter {
    private MainCoursesFragment mainCoursesFragment;
    private OptionalCourseFragment optionalCourseFragment;

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
