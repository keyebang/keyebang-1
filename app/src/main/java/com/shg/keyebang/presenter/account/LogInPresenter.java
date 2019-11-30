package com.shg.keyebang.presenter.account;

import android.content.Intent;

import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.PhoneAccount;
import com.shg.keyebang.services.account.PhoneListener;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.activity.MainActivity;
import com.shg.keyebang.view.activity.account.LoginActivity;

public class LoginPresenter extends BasePresenter {
    private LoginActivity activity;

    public LoginPresenter(LoginActivity activity){
        this.activity = activity;
    }

    public void getSMSCode(String phoneNumber){
        PhoneAccount.sendSMS(phoneNumber, new PhoneListener() {
            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
            }

            @Override
            public void phoneSuccess(String Message) {

            }
        });

    }

    public void loginOrSignUp(String phoneNumber, String code){
        final String message;
        PhoneAccount.checkSMS(phoneNumber, code, new SignUpLogInListener() {
            @Override
            public void loginSuccess(String message) {
                activity.toastAndLog("登录成功");
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void signupSuccess() {
                activity.toastAndLog("注册成功");
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
            }
        });
    }
}
