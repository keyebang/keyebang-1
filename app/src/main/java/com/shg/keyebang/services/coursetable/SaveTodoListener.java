package com.shg.keyebang.services.coursetable;

import com.shg.keyebang.services.BaseListener;

public interface SaveTodoListener extends BaseListener {
    void onSuccess(String message,String objectId);
}
