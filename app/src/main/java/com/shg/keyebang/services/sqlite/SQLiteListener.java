package com.shg.keyebang.services.sqlite;

import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.services.BaseListener;

import java.util.Map;

public interface SQLiteListener extends BaseListener {
    void onSuccess();
}
