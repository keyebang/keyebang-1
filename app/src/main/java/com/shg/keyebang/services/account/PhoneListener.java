package com.shg.keyebang.services.account;

public abstract class PhoneListener {

    public abstract void onFailure(String errMessage);
    public abstract void phoneSuccess(String Message);

}
