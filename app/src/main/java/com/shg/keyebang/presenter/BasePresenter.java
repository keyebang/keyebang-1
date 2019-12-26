package com.shg.keyebang.presenter;

import android.content.Intent;

import com.shg.keyebang.view.activity.BaseActivity;

public abstract class BasePresenter {
    protected void startActivityDirectly(BaseActivity activity, Class<?> cls){
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }
}