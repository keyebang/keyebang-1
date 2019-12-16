package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.services.BaseListener;

import java.util.Map;

public interface GetClassListener extends BaseListener {
    void onSuccess(Map<Course, Todo> table);
}
