package com.shg.keyebang.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.services.account.AccountSystem;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.account.LogInActivity;

public class MainActivity extends MyActivity {
    private Button logOut;


    @Override
    protected void init() {
        logOut = findViewById(R.id.logout);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        logOut.setOnClickListener((v)->{
            if(User.isLogin()) User.logOut();
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        });
    }
}
