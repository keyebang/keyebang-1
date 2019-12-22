package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.model.ViewCourseInfo;
import com.shg.keyebang.services.BaseListener;

public interface GetCourseInfoListener extends BaseListener {
    void onSuccess(ViewCourseInfo viewCourseInfo);
}
