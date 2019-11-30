package com.shg.keyebang.view.activity.account;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.OldLogInPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

public class OldLogInActivity extends BaseActivity {
    private OldLogInPresenter presenter;
    private EditText username;
    private EditText password;
    private TextView toSignUp;
    private Button logInButton;
    private ImageView passwordIcon;
    private boolean isHidePassword = false;

    @Override
    protected void init() {
        presenter = new OldLogInPresenter(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        toSignUp = findViewById(R.id.toSignUp);
        logInButton = findViewById(R.id.loginButton);
        passwordIcon = findViewById(R.id.passwordIcon);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_account_login);
        init();

        presenter.autoLogin();

        logInButton.setOnClickListener((v)->{
            presenter.login(username.getText().toString(), password.getText().toString());
        });

        toSignUp.setOnClickListener((v)->{
            presenter.toSignUp();
        });

        passwordIcon.setOnClickListener((v)->{
            changePasswordType();
        });
    }

    private void changePasswordType(){
        if(isHidePassword){
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordIcon.setImageResource(R.drawable.ic_md_eye_on);
            passwordIcon.setAlpha((float)1.0);
            isHidePassword = false;
        }
        else{
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordIcon.setImageResource(R.drawable.ic_md_eye_off);
            passwordIcon.setAlpha((float)0.4);
            isHidePassword = true;
        }
        password.setSelection(password.getText().toString().length());
    }
}
