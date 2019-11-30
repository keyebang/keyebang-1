package com.shg.keyebang.services.account;

import android.content.Context;
import android.content.SharedPreferences;

import com.shg.keyebang.MyApplication;
import com.shg.keyebang.model.User;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Account {

    public static void signUp(final User user, SignUpLogInListener listener){



    }

    public static void login(final User user, SignUpLogInListener listener){


    }

    public static void logOut(){
        SharedPreferences.Editor editor= MyApplication.getContext().getSharedPreferences("thisAccount", Context.MODE_PRIVATE).edit();
        editor.clear().apply();
        User.logOut();
    }

    public static boolean isLogin(){
        return User.isLogin();
    }

    public static String getSemester(String studentId){
        return "大一下";
    }

    public static String getName(String studentId){
        return "小课同学";
    }

    public static void setAccountSp(String username, String password){
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("thisAccount", Context.MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
}
