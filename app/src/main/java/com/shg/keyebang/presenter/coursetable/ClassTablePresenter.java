package com.shg.keyebang.presenter.coursetable;

import com.shg.keyebang.fakeservices.coursetable.FakeGetTableListener;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.fakeservices.coursetable.FakeTableService;
import com.shg.keyebang.view.activity.coursetable.CourseTableActivity;

import java.util.Map;

public class ClassTablePresenter extends BasePresenter {
    private CourseTableActivity activity;
    String errMessage;

    public ClassTablePresenter(CourseTableActivity activity){
        this.activity = activity;
    }

    public void fakeGetTable(){
        if(true){
            FakeTableService.getTable("User.getCurrentUser(User.class).getStudentId()", new FakeGetTableListener() {
                @Override
                public void onSuccess(Map<Course, Todo> table) {
                    activity.setCourseTable(table);
                }

                @Override
                public void onFailure(String errMsg) {
                    errMessage = errMsg;
                }
            });
        }
    }
}
