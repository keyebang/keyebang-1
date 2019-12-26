package com.shg.keyebang.view.activity.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProfileFragment extends BaseFragment {
    private View view;
    private TextView profileNickname;
    private TextView profileSemester;
    private TextView profileMajor;
    private Button logOut;

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
        profileNickname = view.findViewById(R.id.profileNickname);
        profileSemester = view.findViewById(R.id.profileSemester);
        profileMajor = view.findViewById(R.id.profileMajor);
        init();
        return view;
    }

    @Override
    protected void init() {
        profileNickname.setText(User.getCurrentUser(User.class).getNickname());
        profileSemester.setText(User.getCurrentUser(User.class).getSemester());
        profileMajor.setText(User.getCurrentUser(User.class).getMajor());

        logOut.setOnClickListener((v)->{
            User.logOut();
            startActivityDirectly(LoginActivity.class);
            getActivity().finish();
        });
    }
}
