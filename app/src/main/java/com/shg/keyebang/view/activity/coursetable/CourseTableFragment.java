package com.shg.keyebang.view.activity.coursetable;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewCourse;
import com.shg.keyebang.model.ViewCourseTime;
import com.shg.keyebang.model.ViewTodo;
import com.shg.keyebang.presenter.coursetable.CourseTablePresenter;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CourseTableFragment extends BaseFragment {
    private View view;
    private boolean singleOrDouble = false;
    private CourseTablePresenter presenter;
    private ArrayList<CourseCard> courseCards;
    private SwipeRefreshLayout refreshLayout;
    private ConstraintLayout tableContainer;
    private TitleBarLayout titleBar;
    private TextView semesterTime;
    private TextView date;



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
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_coursetable, container, false);
        refreshLayout = view.findViewById(R.id.refreshTable);
        tableContainer = view.findViewById(R.id.tableContainer);
        titleBar = view.findViewById(R.id.courseTableBar);
        semesterTime = view.findViewById(R.id.semesterTime);
        date = view.findViewById(R.id.date);
        init();
        return view;
    }

    @Override
    protected void init() {
        refreshLayout.setOnRefreshListener(()->{
            presenter.fakeGetTableToFragment();
        });
        presenter.fakeGetTableToFragment();
        presenter.getDate();
        presenter.getSemesterTime();
        titleBar.setTitle(presenter.getTitle());
        //date.setText(presenter.getDate());
    }


    public void setCourseTable(Map<ViewCourse, ViewTodo> classTable){
        for(ViewCourse course: classTable.keySet()){
            ViewTodo todo = classTable.get(course);
            for(ViewCourseTime courseTime: course.getCourseTimes()){
                if(courseTime.getSingleOrDouble() != 0){
                    if((courseTime.getSingleOrDouble() == ViewCourseTime.WEEK_DOUBLE) == singleOrDouble ) continue;
                }
                CourseCard card = new CourseCard(getActivity());
                card.setCourse(course);
                card.setTodo(todo);
                card.setLocation(courseTime);
                card.setSize(courseTime);
                card.setOnClick(this);
                courseCards.add(card);
                tableContainer.addView(card);
            }
        }
        refreshLayout.setRefreshing(false);
    }


    public void setSemesterTime(int thisWeek, boolean singleOrDouble) {
        this.singleOrDouble = singleOrDouble;
        semesterTime.setText("第" + thisWeek + "周" + (singleOrDouble? "单" : "双") + "周");
    }

    public void updateTodoInCourseCard(String courseId, ViewTodo todo){
        for(CourseCard courseCard : courseCards){
            if(courseId.equals(courseCard.getCourseId())) {
                courseCard.setTodo(todo);
                courseCard.setOnClick(this);
            }
        }
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        refreshLayout.setRefreshing(false);
    }
}
