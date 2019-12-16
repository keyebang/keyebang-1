package com.shg.keyebang.services.account;


import com.shg.keyebang.services.BaseListener;

public interface PhoneSignUpLogInListener extends BaseListener {
    void loginSuccess(String message);
    void signupSuccess();

}
