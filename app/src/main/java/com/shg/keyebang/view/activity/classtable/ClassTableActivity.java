package com.shg.keyebang.view.activity.classtable;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.shg.keyebang.R;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.presenter.classtable.ClassTablePresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.layout.CourseCard;

import java.util.ArrayList;
import java.util.Map;

public class ClassTableActivity extends BaseActivity {
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
        setContentView(R.layout.activity_classtable);
        init();

        Map<Course, Todo> classTable = presenter.fakeGetTable();

        for(Course course: classTable.keySet()){
            CourseCard card = new CourseCard(this);
            card.setCourse(course);
            card.setTodo(classTable.get(course));
            card.setLocation(course);
            card.setSize(course);
            cards.add(card);
            tableContainer.addView(card);
        }
        //toastAndLog(tableContainer.getWidth() + "");
    }
}
