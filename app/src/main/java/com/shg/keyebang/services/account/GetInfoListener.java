package com.shg.keyebang.services.account;

import com.shg.keyebang.model.User;
import com.shg.keyebang.services.BaseListener;

public interface GetInfoListener extends BaseListener {

    void onSuccess(User user);
}
