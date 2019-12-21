package com.shg.keyebang.fakeservices.coursetable;

import com.shg.keyebang.fakeservices.BaseListener;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewTodo;

import java.util.Map;

public interface FakeGetTableListener extends BaseListener {
    void onSuccess(Map<ViewCourse, ViewTodo> table);
}


