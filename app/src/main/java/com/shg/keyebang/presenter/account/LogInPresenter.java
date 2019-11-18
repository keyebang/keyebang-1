package com.shg.keyebang.presenter.account;

import android.content.Intent;

import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.Account;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.MainActivity;
import com.shg.keyebang.view.account.LogInActivity;
import com.shg.keyebang.view.account.SignUpActivity;

public class LogInPresenter extends BasePresenter {
    private LogInActivity logInActivity;

    public LogInPresenter(LogInActivity logInActivity){
        this.logInActivity = logInActivity;
    }

    public void login(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Account.login(user, new SignUpLogInListener() {
            @Override
            public void onSuccess(User user, String message) {
                logInActivity.toastAndLog(message);
                Intent intent = new Intent(logInActivity, MainActivity.class);
                logInActivity.startActivity(intent);
            }

            @Override
            public void onFailure(String errMessage) {
                logInActivity.toastAndLog(errMessage);
            }
        });
    }

    public void toSignUp() {
        Intent intent = new Intent(logInActivity, SignUpActivity.class);
        logInActivity.startActivity(intent);
    }
}
