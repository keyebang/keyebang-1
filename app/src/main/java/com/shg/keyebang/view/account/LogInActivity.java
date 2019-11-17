package com.shg.keyebang.view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.shg.keyebang.R;
import com.shg.keyebang.builder.DensityAdapter;
import com.shg.keyebang.model.User;
import com.shg.keyebang.services.account.AccountSystem;
import com.shg.keyebang.services.account.SignUpLogInListener;
import com.shg.keyebang.view.MainActivity;
import com.shg.keyebang.view.MyActivity;

public class LogInActivity extends MyActivity {
    private EditText userName;
    private EditText password;
    private Button logInButton;


    @Override
    protected void init() {
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logInButton = findViewById(R.id.loginbutton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();

        logInButton.setOnClickListener((v)->{
            User user = new User();
            user.setUsername(userName.getText().toString());
            user.setPassword(password.getText().toString());
            AccountSystem.login(user, new SignUpLogInListener() {
                @Override
                public void onSuccess(User user, String message) {
                    toastAndLog(message);
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(String errMessage) {
                    toastAndLog(errMessage);
                }
            });
        });
    }
}
