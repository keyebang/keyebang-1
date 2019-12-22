package com.shg.keyebang.view.activity.account;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lijiankun24.shadowlayout.ShadowLayout;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.presenter.account.LogInPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity {
    private LogInPresenter presenter;
    private ShadowLayout phoneLoginCard;
    private EditText phoneNumber;
    private ConstraintLayout getCode;
    private TextView getCodeHint;
    private EditText code;
    private TextView toCommonLogin;
    private ShadowLayout commonLoginCard;
    private EditText username;
    private EditText password;
    private ConstraintLayout changePasswordVisibility;
    private ImageView passwordIcon;
    private TextView toPhoneLogin;
    private Button entryButton;
    private int timeToGetSMS;
    private Timer timer;
    private boolean isHidePassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        presenter = new LogInPresenter(this);
        phoneLoginCard = findViewById(R.id.phoneLoginCard);
        phoneNumber = findViewById(R.id.phoneLoginPhoneNumber);
        code = findViewById(R.id.phoneLoginCode);
        getCode = findViewById(R.id.getCode);
        getCodeHint = findViewById(R.id.getCodeHint);
        toCommonLogin = findViewById(R.id.toCommonLogin);
        commonLoginCard = findViewById(R.id.commonLoginCard);
        username = findViewById(R.id.commonLoginPhoneNumber);
        password = findViewById(R.id.commonLoginPassword);
        changePasswordVisibility = findViewById(R.id.changePasswordVisibility);
        passwordIcon = findViewById(R.id.loginPasswordIcon);
        toPhoneLogin = findViewById(R.id.toPhoneLogin);
        entryButton = findViewById(R.id.entryButton);
        commonLoginCard.setY(500);

        init();
    }

    @Override
    protected void init() {
        getCode.setOnClickListener(v->presenter.getSMSCode(phoneNumber.getText().toString()));

        entryButton.setOnClickListener(v->presenter.loginOrSignUp(phoneNumber.getText().toString(), code.getText().toString()));

        toCommonLogin.setOnClickListener(v->toCommonLogin());

        toPhoneLogin.setOnClickListener(v->toPhoneLogin());

        changePasswordVisibility.setOnClickListener(v->changePasswordVisibility());
    }

    private void toCommonLogin(){
        ValueAnimator locationAnim = ValueAnimator.ofFloat(0, 500).setDuration(250);
        locationAnim.addUpdateListener((valueAnimator)-> {
            float currentValue = (Float) valueAnimator.getAnimatedValue();
            phoneLoginCard.setY(DisplayUtil.pxTodp(currentValue));
            commonLoginCard.setY(DisplayUtil.pxTodp(500-currentValue));
            commonLoginCard.requestLayout();
            phoneLoginCard.requestLayout();
        });
        locationAnim.start();
        entryButton.setOnClickListener(v->presenter.commonLogin(username.getText().toString(), password.getText().toString()));
    }

    private void toPhoneLogin(){
        ValueAnimator locationAnim = ValueAnimator.ofInt(500, 0).setDuration(250);
        locationAnim.addUpdateListener((valueAnimator)-> {
            int currentValue = (Integer) valueAnimator.getAnimatedValue();
            phoneLoginCard.setY(DisplayUtil.pxTodp(currentValue));
            commonLoginCard.setY(DisplayUtil.pxTodp(500-currentValue));
            commonLoginCard.requestLayout();
            phoneLoginCard.requestLayout();
        });
        locationAnim.start();
        entryButton.setOnClickListener(v->presenter.loginOrSignUp(phoneNumber.getText().toString(), code.getText().toString()));
    }

    public void waitToSendAgain(){

        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(()->{
                    getCodeHint.setText(timeToGetSMS + "s后可重试");
                    timeToGetSMS--;
                    if(timeToGetSMS < 0) timeToGetSMS = 0;
                });
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(()->{
                    getCode.setEnabled(true);
                    getCodeHint.setText("获取验证码");
                    getCodeHint.setAlpha((float)0.7);
                });
                timer.cancel();
            }
        };
        timeToGetSMS = 30;
        getCode.setEnabled(false);
        getCodeHint.setAlpha((float)0.3);
        timer = new Timer();
        timer.schedule(timerTask1, 0, 1000);
        timer.schedule(timerTask2, 30000);
    }

    private void changePasswordVisibility(){
        if(isHidePassword){
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordIcon.setImageResource(R.drawable.ic_md_eye_on);
            passwordIcon.setAlpha((float)0.6);
            isHidePassword = false;
        }
        else{
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordIcon.setImageResource(R.drawable.ic_md_eye_off);
            passwordIcon.setAlpha((float)0.3);
            isHidePassword = true;
        }
        password.setSelection(password.getText().toString().length());
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
    }
}
