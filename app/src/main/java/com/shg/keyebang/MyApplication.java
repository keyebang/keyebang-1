package com.shg.keyebang;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.shg.keyebang.view.activity.main.MainActivity;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Bmob.initialize(this, BuildConfig.APPKEY);
    }

    public static Context getContext(){
        return context;
    }
}