package com.shg.keyebang.presenter.account;

import android.content.Intent;
import android.text.TextUtils;

import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.Account4m3;
import com.shg.keyebang.services.account.GetInfoListener;
import com.shg.keyebang.view.activity.MainActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

public class SignUpPresenter extends BasePresenter {
    private SignUpActivity activity;

    public SignUpPresenter(SignUpActivity signUpActivity){
        this.activity = signUpActivity;
    }

    public void signUp(String nickname, String studentId, String semester, String major, String password){
        if(TextUtils.isEmpty(nickname) || TextUtils.isEmpty(studentId) || TextUtils.isEmpty(semester) || TextUtils.isEmpty(major) || TextUtils.isEmpty(password)){
            activity.toastAndLog("信息未填写完全");
            return;
        }

        if(!isStudentId(studentId)){
            activity.toastAndLog("StudentId格式不正确");
            return;
        }

        User user = User.getCurrentUser(User.class);
        user.setPassword(password);
        user.setStudentId(studentId);
        user.setNickname(nickname);
        user.setSemester(semester);
        user.setMajor(major);

        Account4m3.gatInfo(user, password, studentId, "", new GetInfoListener() {
            @Override
            public void onSuccess(String message) {
                activity.toast(message);
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
            }
        });
    }

    private boolean isStudentId(String studentId){
        if(studentId.length() != 7) return false;
        for (int i = studentId.length(); --i >= 0;){
            if (!Character.isDigit(studentId.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
