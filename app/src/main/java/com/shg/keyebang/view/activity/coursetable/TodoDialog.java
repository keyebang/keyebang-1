package com.shg.keyebang.view.activity.coursetable;

import android.app.DatePickerDialog;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.presenter.coursetable.TodoPresenter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

class TodoDialog extends BottomSheetDialog {
    private final CourseTableFragment fragment;
    private final TodoPresenter presenter;
    private ViewTodo todo;
    private final ViewCourse course;

    private int color;
    private int year = 0;
    private int month;
    private int day;
    private boolean isChange = false;

    private final ImageView save;
    private final ImageView delete;
    private final ImageView deleteCourse;
    private final EditText todoTitle;
    private final EditText todoMessage;
    private final TextView todoTime;
    private final ConstraintLayout setTime;
    private final ConstraintLayout chooseRed;
    private final ConstraintLayout chooseGreen;
    private final ConstraintLayout chooseBlue;
    private final ConstraintLayout courseBg;
    private final TextView courseName;
    private final TextView coursePlace;
    private final TextView courseTeacher;

    TodoDialog(CourseTableFragment fragment, @Nullable ViewTodo todo, @NonNull ViewCourse course){
        super(fragment.getActivity(), R.style.todoDialog);
        setContentView(R.layout.dialog_todo);
        this.fragment = fragment;
        this.todo = todo;
        this.course = course;
        presenter = new TodoPresenter(fragment);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        deleteCourse = findViewById(R.id.deleteCourse);
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
        todoTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                isChange = checkChange();
            }
        });
        todoMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                isChange = checkChange();
            }
        });
        delete.setOnClickListener(v->deleteTodo());
        deleteCourse.setOnClickListener(v->deleteCourse());
        save.setOnClickListener(v->saveTodo());
        setTime.setOnClickListener(v-> chooseTime());
        chooseRed.setOnClickListener(v->setColor(0));
        chooseGreen.setOnClickListener(v->setColor(1));
        chooseBlue.setOnClickListener(v->setColor(2));
    }

    private void initTodo(){
        if(todo != null){
            color = todo.getColor();
            todoTitle.setText(todo.getTodoTitle());
            todoTitle.setSelection(todoTitle.getText().length());
            if(!StringUtil.isSomeNullOrEmpty(todo.getTodoMessage())) {
                todoMessage.setText(todo.getTodoMessage());
                todoMessage.setSelection(todoMessage.getText().length());
            }
            setTime(todo.getDate().get(Calendar.YEAR), todo.getDate().get(Calendar.MONTH), todo.getDate().get(Calendar.DAY_OF_MONTH));
        }
        else {
            color = ViewTodo.COLOR_BLUE;
        }
        ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(color, null));
    }

    private void initCourse() {
        courseName.setText(course.getCourseName());
        coursePlace.setText(course.getClassPlace());
        courseTeacher.setText(course.getTeacher());
    }

    @Override
    public void dismiss() {
        if(isChange){
            onStart();
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
            builder.setMessage("放弃未保存的更改？");
            builder.setPositiveButton("是的", (v1, i1)->super.dismiss());
            builder.setNegativeButton("取消", (v2, i2)->{});
            showAlertDialog(builder);
        }
        else super.dismiss();
    }

    private void chooseTime(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(fragment.getActivity(),
                (datePicker, newYear, newMonth, newDay) -> setTime(newYear, newMonth, newDay),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private void setTime(int y, int m, int d){
        year = y;
        month = m;
        day = d;
        todoTime.setText(m+1 + "." + d);
        isChange = checkChange();
    }

    private void setColor(int i){
        switch (i){
            case 0:
                color = ViewTodo.COLOR_RED;
                break;
            case 1:
                color = ViewTodo.COLOR_GREEN;
                break;
            case 2:
                color = ViewTodo.COLOR_BLUE;
                break;
        }
        ((GradientDrawable)courseBg.getBackground()).setColor(getContext().getResources().getColor(color, null));
        isChange = checkChange();
    }

    private void saveTodo() {
        if(isChange) {
            if (StringUtil.isSomeNullOrEmpty(todoTitle.getText().toString())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                builder.setMessage("您未填写Todo标题");
                builder.setPositiveButton("好", (v1, i1)->{});
                showAlertDialog(builder);
            }
            else if (year == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                builder.setMessage("您未设置时间");
                builder.setPositiveButton("好", (v1, i1)->{});
                showAlertDialog(builder);
            }
            else {
                if (todo == null) {
                    todo = new ViewTodo();
                    todo.setTodoId(course.getTodoId());
                }
                todo.setTodoTitle(todoTitle.getText().toString());
                todo.setTodoMessage(todoMessage.getText().toString());
                todo.setColor(color);
                todo.setDate(new GregorianCalendar(year, month, day));
                presenter.saveTodo(course.getCourseId(), todo);
                isChange = false;
                dismiss();
            }
        }
        else dismiss();
    }

    private void deleteTodo(){
        if(todo == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        builder.setMessage("确定要删除此Todo吗？");
        builder.setPositiveButton("是的", (v1, i1)->{
            presenter.deleteTodo(todo.getTodoId());
            super.dismiss();
        });
        builder.setNegativeButton("取消", (v2, i2)->{});
        showAlertDialog(builder);
    }

    private void deleteCourse() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        builder.setMessage("确定要删除此课程吗？");
        builder.setPositiveButton("是的", (v1, i1)->{
            presenter.deleteCourse(course.getTodoId());
            super.dismiss();
        });
        builder.setNegativeButton("取消", (v2, i2)->{});
        showAlertDialog(builder);
    }

    private boolean checkChange() {
        if(todo == null) return !(StringUtil.isAllNullOrEmpty(todoTitle.getText().toString(), todoMessage.getText().toString()));
        return !(todoTitle.getText().toString().equals(todo.getTodoTitle())
                && todoMessage.getText().toString().equals(todo.getTodoMessage())
                && todo.getColor() == color
                && todo.getDate().get(Calendar.YEAR) == year
                && todo.getDate().get(Calendar.MONTH) == month
                && todo.getDate().get(Calendar.DAY_OF_MONTH) == day);
    }

    private void showAlertDialog(AlertDialog.Builder builder){
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setLayout(DisplayUtil.dpToPx(360), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
