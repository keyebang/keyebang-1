package com.shg.keyebang.presenter.account;

import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;

import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.PhoneAccount;
import com.shg.keyebang.services.account.PhoneListener;
import com.shg.keyebang.services.account.PhoneSignUpLogInListener;
import com.shg.keyebang.view.activity.MainActivity;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

public class LogInPresenter extends BasePresenter {
    private LoginActivity activity;

    public LogInPresenter(LoginActivity activity){
        this.activity = activity;
    }

    public void getSMSCode(String phoneNumber){
        if(TextUtils.isEmpty(phoneNumber)){
            activity.toastAndLog("手机号为空");
            return;
        }

        PhoneAccount.sendSMS(phoneNumber, new PhoneListener() {

            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
                activity.waitToSendAgain();
            }

            @Override
            public void phoneSuccess(String Message) {
                activity.waitToSendAgain();
            }
        });
    }

    public void loginOrSignUp(String phoneNumber, String code){
        if(TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(code)){
            activity.toastAndLog("信息未填写完全");
            return;
        }

        PhoneAccount.checkSMS(phoneNumber, code, new PhoneSignUpLogInListener() {
            @Override
            public void loginSuccess(String message) {
                activity.toastAndLog(message);
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void signupSuccess() {
                Intent intent = new Intent(activity, SignUpActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
            }
        });
    }

    public void commonLogin(String username, String password){

    }
}
