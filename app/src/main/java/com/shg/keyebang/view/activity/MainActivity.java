package com.shg.keyebang.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;
import com.shg.keyebang.view.activity.classtable.ClassTableActivity;

public class MainActivity extends BaseActivity {
    private Button logOut;
    private TextView toClassTable;
    private TextView toTest;


    @Override
    protected void init() {
        logOut = findViewById(R.id.logout);
        toClassTable =findViewById(R.id.hello);
        toTest = findViewById(R.id.test);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        toTest.setOnClickListener((v)->{
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        logOut.setOnClickListener((v)->{
            //if(Account.isLogin()) Account.logOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        toClassTable.setOnClickListener((v)->{
            Intent intent = new Intent(this, ClassTableActivity.class);
            startActivity(intent);
        });
    }
}
