package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.services.BaseListener;

import java.util.ArrayList;

public interface CommentListListener extends BaseListener {
    void onSuccess(ArrayList<ViewComment> viewComments);

}
