package com.shg.keyebang.view.activity.coursetable;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.ViewTodo;

import java.util.Calendar;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CourseCard extends FrameLayout {
    private static final int CARD_WIDTH = 62;
    private static final int CARD_HEIGHT = 72;
    private static final int SPACING = 4;
    private static final int LEFT_BORDER = 0;

    private ViewTodo todo;
    private ViewCourse course;
    private ConstraintLayout cardLayout;
    private TextView courseName;
    private TextView coursePlace;
    private TextView teacher;
    private TextView todoTitle;
    private TextView todoTime;
    private ImageView todoTag;

    public CourseCard(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_coursetable_coursecard, this);
        cardLayout = findViewById(R.id.classCard);
        courseName = findViewById(R.id.courseName);
        coursePlace = findViewById(R.id.coursePlace);
        teacher = findViewById(R.id.teacher);
        todoTitle = findViewById(R.id.todoTitle);
        todoTime = findViewById(R.id.todoTime);
        todoTag = findViewById(R.id.todoTag);
    }

    public void setLocation(ViewCourseTime courseTime){
        int left = DisplayUtil.pxTodp(LEFT_BORDER + (courseTime.getWeekday() - 1) * (CARD_WIDTH + SPACING));
        int top = DisplayUtil.pxTodp((courseTime.getFirstClass() - 1) * (CARD_HEIGHT + SPACING));
        this.setX(left);
        this.setY(top);
    }

    public void setSize(ViewCourseTime courseTime){
        int size = courseTime.getLastClass() - courseTime.getFirstClass();
        cardLayout.setLayoutParams(new FrameLayout.LayoutParams(DisplayUtil.pxTodp(CARD_WIDTH), DisplayUtil.pxTodp(CARD_HEIGHT * (size + 1) + SPACING * size)));
    }

    public void setCourse(ViewCourse course){
        this.course = course;
        courseName.setText(course.getCourseName());
        coursePlace.setText(course.getClassPlace());
        teacher.setText(course.getTeacher());
    }

    public void setTodo(ViewTodo todo){
        this.todo = todo;
        if(this.todo != null) setTodoState(true);
        else setTodoState(false);
    }

    public void setTodoState(boolean exist){
        if(exist){
            todoTitle.setVisibility(View.VISIBLE);
            todoTime.setVisibility(View.VISIBLE);
            todoTag.setVisibility(View.VISIBLE);
            todoTitle.setText(todo.getTodoTitle());
            todoTime.setText((todo.getDate().get(Calendar.MONTH) + 1) + "." + todo.getDate().get(Calendar.DAY_OF_MONTH));
            ((GradientDrawable)cardLayout.getBackground()).setColor(getResources().getColor(todo.getColor(), null));

            courseName.setTextColor(getResources().getColor(R.color.themeColorWhite, null));
            coursePlace.setTextColor(getResources().getColor(R.color.themeColorWhite, null));
            teacher.setTextColor(getResources().getColor(R.color.themeColorWhite, null));
            coursePlace.setAlpha(0.75f);
            teacher.setAlpha(0.75f);
        }
        else{
            todoTitle.setVisibility(View.GONE);
            todoTime.setVisibility(View.GONE);
            todoTag.setVisibility(View.GONE);

            courseName.setTextColor(getResources().getColor(R.color.themeColorBlack, null));
            coursePlace.setTextColor(getResources().getColor(R.color.themeColorBlack, null));
            teacher.setTextColor(getResources().getColor(R.color.themeColorBlack, null));
            coursePlace.setAlpha(0.6f);
            teacher.setAlpha(0.6f);
            ((GradientDrawable)cardLayout.getBackground()).setColor(getResources().getColor(R.color.themeColorWhite, null));
        }
    }

    public void setOnClick(CourseTableFragment fragment){
        this.setOnClickListener(v->{
            TodoDialog dialog = new TodoDialog(fragment, todo, course);
            dialog.show();
        });
    }

    public String getTodoId(){
        return (todo == null)? null : todo.getTodoId();
    }

    public String getCourseId() {
        return course.getCourseId();
    }
}