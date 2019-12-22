package com.shg.keyebang.view.activity.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProfileFragment extends BaseFragment {
    private View view;
    private Button logOut;
    private Button toTest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(view == null){}
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        logOut = view.findViewById(R.id.logout);
        toTest = view.findViewById(R.id.test);

        init();
        return view;
    }

    @Override
    protected void init() {
        toTest.setOnClickListener((v)->{
            startActivityDirectly(SignUpActivity.class);
        });

        logOut.setOnClickListener((v)->{
            User.logOut();
            startActivityDirectly(LoginActivity.class);
            getActivity().finish();
        });
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
    }
}
