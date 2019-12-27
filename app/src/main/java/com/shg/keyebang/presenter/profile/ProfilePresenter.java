package com.shg.keyebang.presenter.profile;

import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.account.AccountInfoService;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.view.activity.profile.UpdatePersonalInfoActivity;

public class ProfilePresenter extends BasePresenter {
    private final UpdatePersonalInfoActivity activity;

    public ProfilePresenter(UpdatePersonalInfoActivity activity) {
        this.activity = activity;
    }

    public void updatePersonalInfo(String nickname, String contact, String semester, String major) {
        if(StringUtil.isSomeNullOrEmpty(nickname, contact, semester, major)){
            activity.showErrorMessage("信息未填写完全");
            return;
        }

        User user = User.getCurrentUser(User.class);
        if(nickname.equals(user.getNickname()) && contact.equals(user.getContactMessage()) && semester.equals(user.getSemester()) && major.equals(user.getMajor())){
            activity.finish();
            return;
        }

        user.setNickname(nickname);
        user.setContactMessage(contact);
        user.setSemester(semester);
        user.setMajor(major);
        AccountInfoService.updateInfo(user, new AddDataListener() {
            @Override
            public void onSuccess(String message) {
                AccountInfoService.fetchUserInfo(new SignUpLogInListener() {
                    @Override
                    public void onSuccess(String message) {
                        activity.toastAndLog("信息更新成功");
                        activity.finish();
                    }

                    @Override
                    public void onFailure(String errMassage) {
                        activity.showErrorMessage(errMassage);
                    }
                });
            }

            @Override
            public void onFailure(String errMassage) {
                activity.showErrorMessage(errMassage);
            }
        });
    }
}
