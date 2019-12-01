package com.shg.keyebang.view.activity.coursetable;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.shg.keyebang.R;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.coursetable.ClassTablePresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.layout.CourseCard;

import java.util.ArrayList;
import java.util.Map;

public class CourseTableActivity extends BaseActivity {
    ClassTablePresenter presenter;
    ConstraintLayout tableContainer;
    ArrayList<CourseCard> cards = new ArrayList<>();

    @Override
    protected void init() {
        presenter = new ClassTablePresenter(this);
        tableContainer = findViewById(R.id.tableContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursetable);
        init();

        if(TextUtils.isEmpty(User.getCurrentUser(User.class).getStudentId())){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        presenter.fakeGetTable();

        //toastAndLog(tableContainer.getWidth() + "");
    }

    public void setCourseTable(Map<Course, Todo> classTable){
        for(Course course: classTable.keySet()){
            CourseCard card = new CourseCard(this);
            card.setCourse(course);
            card.setTodo(classTable.get(course));
            card.setLocation(course);
            card.setSize(course);
            cards.add(card);
            tableContainer.addView(card);
        }
    }
}
