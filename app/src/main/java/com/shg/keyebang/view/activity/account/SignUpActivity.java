package com.shg.keyebang.view.activity.account;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.presenter.account.SignUpPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.shg.keyebang.model.StudentMessage.majors;
import static com.shg.keyebang.model.StudentMessage.semesters;

public class SignUpActivity extends BaseActivity {
    private String semester;
    private String major;
    private SignUpPresenter presenter;
    private EditText nickname;
    private EditText password;
    private EditText contact;
    private ConstraintLayout chooseSemesterButton;
    private TextView semesterMessage;
    private ConstraintLayout chooseMajorButton;
    private TextView majorMessage;
    private ImageView close;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_signup);
        presenter = new SignUpPresenter(this);
        close = findViewById(R.id.delete);
        nickname = findViewById(R.id.signUpNickname);
        password = findViewById(R.id.signUpPassword);
        chooseSemesterButton = findViewById(R.id.chooseSemesterButton);
        semesterMessage = findViewById(R.id.signUpSemesterMessage);
        chooseMajorButton = findViewById(R.id.chooseMajorButton);
        majorMessage = findViewById(R.id.signUpMajorMessage);
        contact = findViewById(R.id.signUpContact);
        signUp = findViewById(R.id.signUpButton);

        init();
    }

    @Override
    protected void init() {
        chooseSemesterButton.setOnClickListener(v->chooseSemester());
        chooseMajorButton.setOnClickListener(v->chooseMajor());

        signUp.setOnClickListener(view ->
            presenter.signUp(
                    nickname.getText().toString(),
                    password.getText().toString(),
                    contact.getText().toString(),
                    semester,
                    major)
        );

        close.setOnClickListener(v->finish());
    }

    private void chooseSemester(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择你的年级");
        builder.setSingleChoiceItems(semesters, -1, (dialog, which) -> {
            semester = semesters[which];
            semesterMessage.setText(semesters[which]);
            semesterMessage.setAlpha(1.0f);
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setLayout(DisplayUtil.dpToPx(360), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void chooseMajor(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择你的专业");
        builder.setSingleChoiceItems(majors, -1, (dialog, which) -> {
            major = majors[which];
            majorMessage.setText(majors[which]);
            majorMessage.setAlpha(1.0f);
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setLayout(DisplayUtil.dpToPx(360), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
