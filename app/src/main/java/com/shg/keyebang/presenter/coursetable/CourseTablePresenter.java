package com.shg.keyebang.presenter.coursetable;

import com.shg.keyebang.aatools.TimeCNUtil;
import com.shg.keyebang.fakeservices.coursetable.FakeGetTableListener;
import com.shg.keyebang.fakeservices.coursetable.SemesterTimeListener;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.fakeservices.coursetable.FakeTableService;
import com.shg.keyebang.view.activity.coursetable.CourseTableFragment;

import java.util.Calendar;
import java.util.Map;

public class CourseTablePresenter extends BasePresenter {
    private CourseTableFragment fragment;

    public CourseTablePresenter(CourseTableFragment fragment){
        this.fragment = fragment;
    }

    public void fakeGetTableToFragment(){
        if(true){
            FakeTableService.getTable("User.getCurrentUser(User.class).getStudentId()", new FakeGetTableListener() {
                @Override
                public void onSuccess(Map<Course, Todo> table) {
                    fragment.setCourseTable(table);
                }

                @Override
                public void onFailure(String errMsg) {
                    fragment.toastAndLog(errMsg);
                }
            });
        }
    }

    public String getTitle(){
        if(!User.isLogin()) return "当前用户未登录";
        String nickname = User.getCurrentUser(User.class).getNickname();
        String greeting = TimeCNUtil.getGreeting(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        return greeting + " " + nickname;
    }

    public void getSemesterTime(){
        FakeTableService.getSemesterTime(new SemesterTimeListener() {
            @Override
            public void onSuccess(String time) {
                fragment.setSemesterTime(time);
            }

            @Override
            public void onFailure(String errMsg) {
                fragment.toastAndLog(errMsg);
            }
        });
    }

    public String getDate() {
        Calendar date = Calendar.getInstance();
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        String weekday = TimeCNUtil.weekdayToCN(date.get(Calendar.DAY_OF_WEEK));
        return month + "月" + day + "日 " +  "星期" + weekday;
    }


}
