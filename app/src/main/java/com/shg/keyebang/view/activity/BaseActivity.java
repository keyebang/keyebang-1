package com.shg.keyebang.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shg.keyebang.aatools.DisplayUtil;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "KYB";

    abstract protected void init();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayUtil.setCustomDensity(this, getApplication());
    }

    public void toastAndLog(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, message);
    }

    public abstract void showErrorMessage(String errMsg);

    protected void startActivityDirectly(Class<?> cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
