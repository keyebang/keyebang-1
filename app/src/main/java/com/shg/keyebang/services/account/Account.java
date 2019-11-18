package com.shg.keyebang.services.account;


import com.shg.keyebang.model.User;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Account {

    public static void signUp(final User user, SignUpLogInListener listener){

        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User u, BmobException e) {
                if(e == null) listener.onSuccess(u, u.getUsername());
                else listener.onFailure(e.getErrorCode() + ": " + e.getMessage());
            }
        });
    }

    public static void login(final User user, SignUpLogInListener listener){

        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e == null) listener.onSuccess(user, user.getUsername());
                else listener.onFailure(e.getErrorCode() + ": " + e.getMessage());
            }
        });
    }

    public static void logOut(){
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
}
