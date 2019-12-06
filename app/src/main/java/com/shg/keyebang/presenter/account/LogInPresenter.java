package com.shg.keyebang.presenter.account;

import com.shg.keyebang.aatools.Strings;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.PhoneAccount;
import com.shg.keyebang.services.account.PhoneListener;
import com.shg.keyebang.services.account.PhoneSignUpLogInListener;
import com.shg.keyebang.view.activity.main.MainActivity;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

public class LoginPresenter extends BasePresenter {
    private LoginActivity activity;

    public LoginPresenter(LoginActivity activity){
        this.activity = activity;
    }

    public void getSMSCode(String phoneNumber){
        if(Strings.isNullOrEmpty(phoneNumber)){
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
        if(Strings.isNullOrEmpty(phoneNumber, code)){
            activity.toastAndLog("信息未填写完全");
            return;
        }

        PhoneAccount.checkSMS(phoneNumber, code, new PhoneSignUpLogInListener() {
            @Override
            public void loginSuccess(String message) {
                startActivityDirectly(activity, MainActivity.class);
            }

            @Override
            public void signupSuccess() {
                startActivityDirectly(activity, SignUpActivity.class);
            }

            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
            }
        });
    }

    public void commonLogin(String username, String password){
        if(Strings.isNullOrEmpty(username, password)){
            activity.toastAndLog("信息未填写完全");
            return;
        }
    }
}
