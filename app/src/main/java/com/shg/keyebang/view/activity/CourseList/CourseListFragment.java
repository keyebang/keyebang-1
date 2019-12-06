package com.shg.keyebang.view.activity.CourseList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shg.keyebang.R;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CourseListFragment extends BaseFragment  {
    private Button logOut;
    private Button toTest;

    @Override
    protected void init() {
        toTest.setOnClickListener((v)->{
            startActivityDirectly(SignUpActivity.class);
        });

        logOut.setOnClickListener((v)->{
            startActivityDirectly(LoginActivity.class);
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_courselist, container, false);

        logOut = view.findViewById(R.id.logout);
        toTest = view.findViewById(R.id.test);
        init();
        return view;
    }
}
