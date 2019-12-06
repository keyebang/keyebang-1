package com.shg.keyebang.fakeservices.coursetable;

import com.shg.keyebang.fakeservices.BaseListener;

public interface SemesterTimeListener extends BaseListener {
    void onSuccess(String time);
}
