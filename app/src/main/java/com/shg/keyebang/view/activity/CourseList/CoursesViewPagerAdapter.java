package com.shg.keyebang.view.activity.CourseList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CoursesViewPagerAdapter extends FragmentPagerAdapter {
    private MainCoursesFragment mainCoursesFragment;
    private OptionalCourseFragment optionalCourseFragment;
    private static String[] titles = new String[]{"必修课", "选修课"};

    CoursesViewPagerAdapter(FragmentManager manager, MainCoursesFragment mainCoursesFragment, OptionalCourseFragment optionalCourseFragment){
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
