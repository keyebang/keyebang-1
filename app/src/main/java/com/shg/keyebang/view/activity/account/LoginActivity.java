package com.shg.keyebang.view.activity.account;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.LoginPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

public class LoginActivity extends BaseActivity {
    private EditText phoneNumber;
    private EditText code;
    private ConstraintLayout getCode;
    private Button loginSignUpButton;
    private LoginPresenter presenter;

    @Override
    protected void init() {
        phoneNumber = findViewById(R.id.commonLoginPhoneNumber);
        code = findViewById(R.id.commonLoginPassword);
        getCode = findViewById(R.id.getCode);
        loginSignUpButton =findViewById(R.id.loginButton);
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        init();

        getCode.setOnClickListener((v)->{
            presenter.getSMSCode(phoneNumber.getText().toString());
        });

        loginSignUpButton.setOnClickListener((v)->{
            presenter.loginOrSignUp(phoneNumber.getText().toString(), code.getText().toString());
        });

    }
}
