package com.shg.keyebang.services.account;

public abstract class PhoneListener {
    public abstract void loginSuccess(String message);
    public abstract void signinSuccess();
    public abstract void onFailure(String errMessage);
    public abstract void phoneSuccess(String Message);

}
