package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.services.BaseListener;

import java.util.Map;

public interface GetClassListener extends BaseListener {
    void onSuccess(Map<ViewCourse, ViewTodo> table);
}
