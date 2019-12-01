package com.shg.keyebang.fakeservices.coursetable;

import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import java.util.Map;

public interface FakeGetTableListener {
    void onSuccess(Map<Course, Todo> table);
    void onFailure(String errMessage);
}
