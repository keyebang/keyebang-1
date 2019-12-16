package com.shg.keyebang.view.activity.coursedetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.presenter.coursedetail.CourseDetailPresenter;
import com.shg.keyebang.view.activity.BaseActivity;

public class CourseDetailActivity extends BaseActivity {
    private String courseName;
    private String courseTeacher;
    private CourseDetailPresenter presenter;
    private TextView courseIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        presenter = new CourseDetailPresenter(this);
        courseName = getIntent().getStringExtra("courseName" );
        courseTeacher = getIntent().getStringExtra("courseTeacher" );
        courseIdTextView = findViewById(R.id.courseId);
        init();
    }

    @Override
    protected void init() {
        if(!StringUtil.isAllNullOrEmpty(courseName)) courseIdTextView.setText(courseName);
    }
}
