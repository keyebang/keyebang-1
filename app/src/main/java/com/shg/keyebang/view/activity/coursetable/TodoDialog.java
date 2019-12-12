package com.shg.keyebang.view.activity.coursetable;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shg.keyebang.R;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

public class TodoDialog extends BottomSheetDialog {
    private Todo todo;
    private Course course;
    private NestedScrollView dialogBg;
    private ImageView save;
    private ImageView close;
    private EditText todoTitle;
    private EditText todoMessage;
    private TextView todoTime;
    private ConstraintLayout setTime;
    private ConstraintLayout chooseRed;
    private ConstraintLayout chooseGreen;
    private ConstraintLayout chooseBlue;
    private ConstraintLayout courseBg;
    private TextView courseName;
    private TextView coursePlace;
    private TextView courseTeacher;

    public TodoDialog(Context context, @Nullable Todo todo, @NonNull Course course){
        super(context, R.style.todoDialog);
        setContentView(R.layout.dialog_todo);
        this.todo = todo;
        this.course = course;
        dialogBg = findViewById(R.id.dialogBg);
        save = findViewById(R.id.save);
        close = findViewById(R.id.close);
        todoTitle = findViewById(R.id.todoTitle);
        todoMessage = findViewById(R.id.todoMessage);
        setTime = findViewById(R.id.chooseTime);
        todoTime = findViewById(R.id.todoTime);
        chooseRed = findViewById(R.id.chooseRed);
        chooseGreen = findViewById(R.id.chooseGreen);
        chooseBlue = findViewById(R.id.chooseBlue);
        courseBg = findViewById(R.id.courseBg);
        courseName = findViewById(R.id.courseName);
        coursePlace = findViewById(R.id.coursePlace);
        courseTeacher = findViewById(R.id.courseTeacher);
        init();
    }

    private void init(){
        initTodo();
        initCourse();
        close.setOnClickListener(v->{});
        save.setOnClickListener(v->{});
        setTime.setOnClickListener(v->{});
        chooseRed.setOnClickListener(v->setColor(0));
        chooseGreen.setOnClickListener(v->setColor(1));
        chooseBlue.setOnClickListener(v->setColor(2));
    }

    private void initTodo(){
        if(todo != null){
            todoTitle.setText(todo.getTodoTitle());
            todoTitle.setSelection(todoTitle.getText().length());
            todoMessage.setText(todo.getTodoMessage());
            todoMessage.setSelection(todoMessage.getText().length());
            ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(todo.getColor(), null));
        }
        else ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(R.color.cardColorBlue, null));
    }

    private void initCourse() {
        courseName.setText(course.getClassName());
        coursePlace.setText(course.getClassPlace());
        courseTeacher.setText(course.getTeacher());
    }

    private void setColor(int i){
        switch (i){
            case 0:
                ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(Todo.COLOR_RED, null));
                break;
            case 1:
                ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(Todo.COLOR_GREEN, null));
                break;
            case 2:
                ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(Todo.COLOR_BLUE, null));
                break;
        }
    }




}
