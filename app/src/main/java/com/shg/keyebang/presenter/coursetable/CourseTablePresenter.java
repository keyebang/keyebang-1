package com.shg.keyebang.presenter.coursetable;

import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.coursetable.CourseTable;
import com.shg.keyebang.services.coursetable.GetClassListener;
import com.shg.keyebang.services.coursetable.GetSemesterTimeListener;
import com.shg.keyebang.services.coursetable.GetSemesterTimeService;
import com.shg.keyebang.view.activity.coursetable.CourseTableFragment;

import java.util.Calendar;
import java.util.Map;

public class CourseTablePresenter extends BasePresenter {
    private final CourseTableFragment fragment;

    public CourseTablePresenter(CourseTableFragment fragment){
        this.fragment = fragment;
    }

    public void getTimeAndTable(){
        if(User.isLogin()){
            GetSemesterTimeService.getSemesterTime(new GetSemesterTimeListener() {
                @Override
                public void onSuccess(int num) {
                    boolean singleOrDouble = num%2 != 0;
                    fragment.setSemesterTime(num, singleOrDouble);
                    getCourseTable();
                }

                @Override
                public void onFailure(String errMassage) {
                    fragment.setSemesterTime(0, false);
                }
            });
        }
        else fragment.showErrorMessage("你未登录");
    }

    private void getCourseTable(){

        if (User.isLogin()){
            CourseTable.getClass(new GetClassListener() {
                @Override
                public void onSuccess(Map<ViewCourse, ViewTodo> table) {
                    fragment.setCourseTable(table);
                }

                @Override
                public void onFailure(String errMassage) {
                    fragment.showErrorMessage(errMassage);
                }
            });
        }
        else fragment.showErrorMessage("你未登录");
    }

    public String getTitle(){
        if(!User.isLogin()) return "当前用户未登录";
        String nickname = User.getCurrentUser(User.class).getNickname();
        String greeting = TimeCNUtil.getGreeting(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        return greeting + " " + nickname;
    }
}
