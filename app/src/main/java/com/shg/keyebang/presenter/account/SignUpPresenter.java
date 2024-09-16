package com.shg.keyebang.presenter.account;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.AccountInfoService;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.view.activity.main.MainActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;

public class SignUpPresenter extends BasePresenter {
    private final SignUpActivity activity;

    public SignUpPresenter(SignUpActivity signUpActivity){
        this.activity = signUpActivity;
    }

    public void signUp(String nickname, String password, String contact, String semester, String major){
        if(StringUtil.isSomeNullOrEmpty(nickname, semester, major, password, contact)){
            activity.showErrorMessage("信息未填写完全");
            return;
        }

        User user = User.getCurrentUser(User.class);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setSemester(semester);
        user.setMajor(major);
        user.setContactMessage(contact);

        AccountInfoService.setInfo(user, new AddDataListener() {
            @Override
            public void onSuccess(String message) {
                startActivityDirectly(activity, MainActivity.class);
            }

            @Override
            public void onFailure(String errMessage) {
                activity.showErrorMessage(errMessage);
            }
        });
    }
}
