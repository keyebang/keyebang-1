package com.shg.keyebang.view.account;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.SignUpPresenter;
import com.shg.keyebang.view.BaseActivity;

public class SignUpActivity extends BaseActivity {
    private SignUpPresenter presenter;
    private EditText username;
    private EditText studentId;
    private EditText nickname;
    private EditText password;
    private EditText confirmPassword;
    private TextView toLogIn;
    private ImageView passwordIcon;
    private Button signUp;
    private boolean isHidePassword = false;

    @Override
    protected void init() {
        presenter = new SignUpPresenter(this);
        username = findViewById(R.id.newUsername);
        studentId = findViewById(R.id.studentId);
        nickname = findViewById(R.id.nickname);
        password = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        toLogIn = findViewById(R.id.toLogIn);
        signUp = findViewById(R.id.signUpButton);
        passwordIcon = findViewById(R.id.passwordIcon);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);
        init();

        signUp.setOnClickListener((v)->{
            presenter.signUp(
                    username.getText().toString(),
                    studentId.getText().toString(),
                    nickname.getText().toString(),
                    password.getText().toString(),
                    confirmPassword.getText().toString()
            );
        });

        toLogIn.setOnClickListener((v)->{
            presenter.toLogIn();
        });

        passwordIcon.setOnClickListener((v)->{
            changePasswordType();
        });
    }

    private void changePasswordType(){
        if(isHidePassword){
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordIcon.setImageResource(R.drawable.ic_remove_red_eye_black_24dp);
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
