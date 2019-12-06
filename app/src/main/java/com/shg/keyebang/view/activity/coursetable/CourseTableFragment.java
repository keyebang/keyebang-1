package com.shg.keyebang.view.activity.coursetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.presenter.coursetable.CourseTablePresenter;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.view.CourseCard;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CourseTableFragment extends BaseFragment {
    private View view;
    private CourseTablePresenter presenter;
    private ArrayList<CourseCard> courseCards;
    private ConstraintLayout tableContainer;
    private TextView title;
    private TextView time;


    @Override
    protected void init() {
        presenter.fakeGetTableToFragment();
        presenter.getSemesterTime();
        title.setText(presenter.getTitle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(view == null){
            courseCards = new ArrayList<>();
            presenter = new CourseTablePresenter(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coursetable, container, false);
        if(/*User.isLogin()*/true) {
            tableContainer = view.findViewById(R.id.tableContainer);
            title = view.findViewById(R.id.courseTableTitle);
            time = view.findViewById(R.id.courseTableTime);
            init();
        }
        return view;
    }



    public void setCourseTable(Map<Course, Todo> classTable){
        for(Course course: classTable.keySet()){
            CourseCard card = new CourseCard(getActivity());
            card.setCourse(course);
            card.setTodo(classTable.get(course));
            card.setLocation(course);
            card.setSize(course);
//            card.setOnClickListener(v->{
//                startActivityDirectly(TodoActivity.class);
//            });
            courseCards.add(card);
            tableContainer.addView(card);
        }
    }


    public void setSemesterTime(String textTime) {
        time.setText(textTime);
    }
}
