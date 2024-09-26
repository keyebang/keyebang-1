package com.shg.keyebang.view.activity.profile;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.profile.ProfilePresenter;
import com.shg.keyebang.view.activity.BaseActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import static com.shg.keyebang.model.StudentMessage.majors;
import static com.shg.keyebang.model.StudentMessage.semesters;

public class UpdatePersonalInfoActivity extends BaseActivity {
    private String semester;
    private String major;
    private ProfilePresenter presenter;
    private EditText nickname;
    private EditText contact;
    private ConstraintLayout chooseSemesterButton;
    private TextView semesterMessage;
    private ConstraintLayout chooseMajorButton;
    private TextView majorMessage;
    private ImageView close;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update_personal_info);
        presenter = new ProfilePresenter(this);
        close = findViewById(R.id.close);
        nickname = findViewById(R.id.updateNickname);
        contact = findViewById(R.id.updateContact);
        chooseSemesterButton = findViewById(R.id.chooseSemesterButton);
        semesterMessage = findViewById(R.id.updateSemesterMessage);
        chooseMajorButton = findViewById(R.id.chooseMajorButton);
        majorMessage = findViewById(R.id.updateMajorMessage);
        save = findViewById(R.id.updateButton);
        init();
    }

    @Override
    protected void init() {
        if(User.isLogin()){
            User user = User.getCurrentUser(User.class);
            if(!StringUtil.isAllNullOrEmpty(user.getNickname())) nickname.setText(user.getNickname());
            if(!StringUtil.isAllNullOrEmpty(user.getContactMessage())) contact.setText(user.getContactMessage());
            if(!StringUtil.isAllNullOrEmpty(user.getSemester())) {
                semesterMessage.setText(user.getSemester());
                semesterMessage.setAlpha(1.0f);
                semester = user.getSemester();
            }
            if(!StringUtil.isAllNullOrEmpty(user.getMajor())) {
                majorMessage.setText(user.getMajor());
                majorMessage.setAlpha(1.0f);
                major = user.getMajor();
            }
        }

        chooseSemesterButton.setOnClickListener(v->chooseSemester());
        chooseMajorButton.setOnClickListener(v->chooseMajor());

        save.setOnClickListener(view ->
                presenter.updatePersonalInfo(
                        nickname.getText().toString(),
                        contact.getText().toString(),
                        semester,
                        major
                )
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
