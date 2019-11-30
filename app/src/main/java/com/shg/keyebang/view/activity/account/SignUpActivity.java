package com.shg.keyebang.view.activity.account;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.SignUpPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

public class SignUpActivity extends BaseActivity {
    private SignUpPresenter presenter;
    private EditText semester;
    private EditText studentId;
    private EditText nickname;
    private EditText password;
    private EditText major;
    private TextView toLogIn;
    private ImageView passwordIcon;
    private Button signUp;
    private boolean isHidePassword = false;

    @Override
    protected void init() {
        presenter = new SignUpPresenter(this);
        studentId = findViewById(R.id.signUpStudentId);
        nickname = findViewById(R.id.signUpNickname);
        password = findViewById(R.id.signUpPassword);
        semester = findViewById(R.id.signUpSemester);
        major = findViewById(R.id.signUpMajor);
        //passwordIcon = findViewById(R.id.passwordIcon);
        signUp = findViewById(R.id.signUpButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);
        init();


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
