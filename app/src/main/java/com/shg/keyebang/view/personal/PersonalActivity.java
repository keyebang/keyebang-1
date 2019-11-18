package com.shg.keyebang.view.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shg.keyebang.R;
import com.shg.keyebang.view.BaseActivity;

public class PersonalActivity extends BaseActivity {


    @Override
    protected void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
    }
}
