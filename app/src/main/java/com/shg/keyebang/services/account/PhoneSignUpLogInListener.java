package com.shg.keyebang.services.account;


public abstract class PhoneSignUpLogInListener {
    public abstract void loginSuccess(String message);
    public abstract void signupSuccess();
    public abstract void onFailure(String errMessage);
}
