package com.shg.keyebang.fakeservices.coursetable;

import com.shg.keyebang.fakeservices.BaseListener;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import java.util.Map;

public interface FakeGetTableListener extends BaseListener {
    void onSuccess(Map<Course, Todo> table);
}


