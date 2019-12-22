package com.shg.keyebang.presenter.account;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.Account;
import com.shg.keyebang.services.account.PhoneAccount;
import com.shg.keyebang.services.account.PhoneListener;
import com.shg.keyebang.services.account.PhoneSignUpLogInListener;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.activity.main.MainActivity;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

public class LogInPresenter extends BasePresenter {
    private LoginActivity activity;

    public LogInPresenter(LoginActivity activity){
        this.activity = activity;
    }

    public void getSMSCode(String phoneNumber){
        if(StringUtil.isSomeNullOrEmpty(phoneNumber)){
            activity.toastAndLog("手机号为空");
            return;
        }

        PhoneAccount.sendSMS(phoneNumber, new PhoneListener() {
            @Override
            public void onFailure(String errMessage) {
                activity.showErrorMessage(errMessage);
            }

            @Override
            public void phoneSuccess(String Message) {

            }
        });
        activity.waitToSendAgain();
    }

    public void loginOrSignUp(String phoneNumber, String code){
        if(StringUtil.isSomeNullOrEmpty(phoneNumber, code)){
            activity.showErrorMessage("信息未填写完全");
            return;
        }

        PhoneAccount.checkSMS(phoneNumber, code, new PhoneSignUpLogInListener() {
            @Override
            public void loginSuccess(String message) {
                startActivityDirectly(activity, MainActivity.class);
                activity.finish();
            }

            @Override
            public void signupSuccess() {
                startActivityDirectly(activity, SignUpActivity.class);
            }

            @Override
            public void onFailure(String errMessage) {
                activity.showErrorMessage(errMessage);
            }
        });
    }

    public void commonLogin(String username, String password){
        if(StringUtil.isSomeNullOrEmpty(username, password)){
            activity.showErrorMessage("信息未填写完全");
            return;
        }

        Account.login(username, password, new SignUpLogInListener() {
            @Override
            public void onSuccess(String message) {
                startActivityDirectly(activity, MainActivity.class);
                activity.finish();
            }

            @Override
            public void onFailure(String errMessage) {
                activity.toastAndLog(errMessage);
            }
        });
    }
}
