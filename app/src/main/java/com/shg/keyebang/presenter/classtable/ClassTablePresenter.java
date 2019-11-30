package com.shg.keyebang.presenter.classtable;

import android.util.Log;

import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.classtable.FakeTableService;
import com.shg.keyebang.view.activity.classtable.ClassTableActivity;

import java.util.HashMap;
import java.util.Map;

public class ClassTablePresenter extends BasePresenter {
    private ClassTableActivity activity;
    Map<Course, Todo> classTable = new HashMap<>();
    String errMessage;

    public ClassTablePresenter(ClassTableActivity activity){
        this.activity = activity;
    }

    public Map<Course, Todo> fakeGetTable(){
        if(true){
            FakeTableService.getTable("User.getCurrentUser(User.class).getStudentId()", new FakeTableService.FakeGetTableListener() {
                @Override
                public void onSuccess(Map<Course, Todo> table) {
                    classTable = table;
                }

                @Override
                public void onFailure(String errMsg) {
                    errMessage = errMsg;
                }
            });
        }
        return classTable;
    }
}
