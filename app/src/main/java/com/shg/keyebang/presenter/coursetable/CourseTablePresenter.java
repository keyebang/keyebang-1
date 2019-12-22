package com.shg.keyebang.presenter.coursetable;

import android.util.Log;

import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.fakeservices.coursetable.FakeGetTableListener;
import com.shg.keyebang.fakeservices.coursetable.SemesterTimeListener;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.fakeservices.coursetable.FakeTableService;
import com.shg.keyebang.services.coursetable.CourseTable;
import com.shg.keyebang.services.coursetable.GetClassListener;
import com.shg.keyebang.view.activity.coursetable.CourseTableFragment;

import java.util.Calendar;
import java.util.Map;

public class CourseTablePresenter extends BasePresenter {
    private CourseTableFragment fragment;

    public CourseTablePresenter(CourseTableFragment fragment){
        this.fragment = fragment;
    }

    public void fakeGetTableToFragment(){

        if (User.getCurrentUser(User.class) != null){
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

    public void getSemesterTime(){
        int week = 10;
        boolean singleOrDouble = week%2 != 0;
        fragment.setSemesterTime(week, singleOrDouble);
    }

    public String getDate() {
        Calendar date = Calendar.getInstance();
        int month = date.get(Calendar.MONTH) ;
        int day = date.get(Calendar.DAY_OF_MONTH);
        String weekday = TimeCNUtil.weekdayToCN(date.get(Calendar.DAY_OF_WEEK));
        return month + "月" + day + "日 " +  "星期" + weekday;
    }
}
