package com.shg.keyebang.services.account;

import com.shg.keyebang.model.User;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class PhoneAccount {

    public static void sendSMS(String phoneNumber, PhoneListener listener){

        BmobSMS.requestSMSCode(phoneNumber, "", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    listener.phoneSuccess("发送验证码成功，短信ID：" + smsId + "\n");
                } else {
                    listener.onFailure("发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                }
            }
        });
    }

    public static void checkSMS(String phoneNumber, String code,PhoneSignUpLogInListener listener){

        BmobUser.signOrLoginByMobilePhone(phoneNumber, code, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {

                if (e == null) {
                    if(user.getNickname()==null){
                        listener.signupSuccess();
                    }
                    else{
                    listener.loginSuccess("短信登录成功：" + user.getUsername());}
                } else {
                    listener.onFailure("短信注册或登录失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                }
            }
        });
    }

}
