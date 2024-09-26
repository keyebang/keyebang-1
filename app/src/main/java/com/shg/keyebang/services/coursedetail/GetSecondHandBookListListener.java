package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.model.ViewSecondHandBook;
import com.shg.keyebang.services.BaseListener;

import java.util.ArrayList;

public interface GetSecondHandBookListListener extends BaseListener {
    void onSuccess(ArrayList<ViewSecondHandBook> books);

}
