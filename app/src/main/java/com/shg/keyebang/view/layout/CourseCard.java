package com.shg.keyebang.view.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lijiankun24.shadowlayout.ShadowLayout;
import com.shg.keyebang.R;
import com.shg.keyebang.builder.DisplayAdapter;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;

import java.util.Calendar;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CourseCard extends FrameLayout implements View.OnClickListener {
    private static int boxWidth = 64;
    private static int boxHeight = 72;
    private static int spacing = 4;

    private ShadowLayout cardBg;
    private ConstraintLayout classCard;
    private TextView className;
    private TextView classPlace;
    private TextView teacher;
    private TextView todoTitle;
    private TextView todoTime;
    private ImageView cardTag;
    private ImageView todoBg;

    public CourseCard(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.coursetable_coursecard, this);

        cardBg = findViewById(R.id.cardBg);
        classCard = findViewById(R.id.classCard);
        className = findViewById(R.id.className);
        classPlace = findViewById(R.id.classPlace);
        teacher = findViewById(R.id.teacher);
        todoTitle = findViewById(R.id.todoTitle);
        todoTime = findViewById(R.id.todoTime);
        cardTag = findViewById(R.id.cardTag);
        todoBg = findViewById(R.id.todoBg);
    }

    @Override
    public void onClick(View view) {

    }

    public void setLocation(Course course){

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)cardBg.getLayoutParams();
        //lp.setMargins(50,50,0,0);

        lp.setMargins(DisplayAdapter.pxTodp(4 + (course.getWeekday() - 1) * (boxWidth + spacing)), DisplayAdapter.pxTodp((course.getFirstClass() - 1) * (boxHeight + spacing)), 0, 0);
    }

    public void setSize(Course course){
        int size = course.getLastClass() - course.getFirstClass();
        classCard.setLayoutParams(new RelativeLayout.LayoutParams(DisplayAdapter.pxTodp(boxWidth), DisplayAdapter.pxTodp(boxHeight * (size + 1) + spacing * size)));
    }

    public void setColor(String color){

    }

    public void setCourse(Course course){
        className.setText(course.getClassName());
        classPlace.setText(course.getClassPlace());
        teacher.setText(course.getTeacher());
    }

    public void setTodo(Todo todo){
        if(todo != null){
            todoBg.setVisibility(View.VISIBLE);
            todoTitle.setVisibility(View.VISIBLE);
            todoTime.setVisibility(View.VISIBLE);
            todoTitle.setText(todo.getTodoTitle());
            todoTime.setText((todo.getDate().get(Calendar.MONTH) + 1) + "." + todo.getDate().get(Calendar.DAY_OF_MONTH));
        }
        else{
            todoBg.setVisibility(View.GONE);
            todoTitle.setVisibility(View.GONE);
            todoTime.setVisibility(View.GONE);
        }
    }
}