package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.services.BaseListener;

import java.util.ArrayList;

public interface BookListListener extends BaseListener {
    void onSuccess(ArrayList<ViewBook> books);
}
