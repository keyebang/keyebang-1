package com.shg.keyebang.view;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public abstract class MyActivity extends AppCompatActivity {
    private static final String TAG = "KYB";

    abstract protected void init();

    public void toastAndLog(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.d(TAG, message);
    }
}
