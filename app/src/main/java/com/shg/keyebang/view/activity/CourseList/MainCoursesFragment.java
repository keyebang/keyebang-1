package com.shg.keyebang.view.activity.CourseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shg.keyebang.R;
import com.shg.keyebang.view.activity.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainCoursesFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_main, container, false);
        init();
        return view;
    }

    @Override
    protected void init() {

    }
}
