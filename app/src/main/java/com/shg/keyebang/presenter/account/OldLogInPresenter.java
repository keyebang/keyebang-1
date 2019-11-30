package com.shg.keyebang.presenter.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.shg.keyebang.MyApplication;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.account.OldLogInActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

public class OldLogInPresenter extends BasePresenter {
    private OldLogInActivity oldLogInActivity;

    public OldLogInPresenter(OldLogInActivity oldLogInActivity){
        this.oldLogInActivity = oldLogInActivity;
    }

    public void login(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

    }

    public void autoLogin() {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("thisAccount", Context.MODE_PRIVATE);
        if(sharedPreferences == null) return;
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) return;
        login(username, password);
    }

    public void toSignUp() {
        Intent intent = new Intent(oldLogInActivity, SignUpActivity.class);
        oldLogInActivity.startActivity(intent);
    }


}
