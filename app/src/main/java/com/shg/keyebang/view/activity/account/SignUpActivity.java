package com.shg.keyebang.view.activity.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shg.keyebang.R;
import com.shg.keyebang.presenter.account.SignUpPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

public class SignUpActivity extends BaseActivity {
    private SignUpPresenter presenter;
    private EditText semester;
    private EditText nickname;
    private EditText password;
    private EditText major;
    private EditText contact;
    private ImageView passwordIcon;
    private ImageView close;
    private Button signUp;
    private boolean isHidePassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);
        presenter = new SignUpPresenter(this);
        close = findViewById(R.id.delete);
        nickname = findViewById(R.id.signUpNickname);
        password = findViewById(R.id.signUpPassword);
        semester = findViewById(R.id.signUpSemester);
        major = findViewById(R.id.signUpMajor);
        contact = findViewById(R.id.signUpContact);
        //passwordIcon = findViewById(R.id.passwordIcon);
        signUp = findViewById(R.id.signUpButton);

        init();
    }

    @Override
    protected void init() {
        signUp.setOnClickListener(view -> {
            presenter.signUp(
                    nickname.getText().toString(),
                    semester.getText().toString(),
                    major.getText().toString(),
                    password.getText().toString(),
                    contact.getText().toString()
            );
        });

        close.setOnClickListener(v->{
            finish();
        });
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
    }
}
