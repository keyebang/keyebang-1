package com.shg.keyebang.services.account;


public abstract class SignUpLogInListener {
    public abstract void loginSuccess(String message);
    public abstract void signupSuccess();
    public abstract void onFailure(String errMessage);
}
