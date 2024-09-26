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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProfileFragment extends BaseFragment {
    private View view;
    private TextView profileNickname;
    private TextView profileSemester;
    private TextView profileMajor;
    private Button updatePersonalInfo;
    private TextView logOut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        updatePersonalInfo = view.findViewById(R.id.updateInfoButton);
        profileNickname = view.findViewById(R.id.profileNickname);
        profileSemester = view.findViewById(R.id.profileSemester);
        profileMajor = view.findViewById(R.id.profileMajor);
        logOut = view.findViewById(R.id.logOutButton);
        init();
        return view;
    }

    @Override
    protected void init() {
        updatePersonalInfo.setOnClickListener(v->startActivityDirectly(UpdatePersonalInfoActivity.class));
        logOut.setOnClickListener(v->{
            User.logOut();
            startActivityDirectly(LoginActivity.class);
            getActivity().finish();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        profileNickname.setText(User.getCurrentUser(User.class).getNickname());
        profileSemester.setText(User.getCurrentUser(User.class).getSemester());
        profileMajor.setText(User.getCurrentUser(User.class).getMajor());
    }
}
