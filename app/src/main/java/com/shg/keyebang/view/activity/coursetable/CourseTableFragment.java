package com.shg.keyebang.view.activity.coursetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shg.keyebang.R;
import com.shg.keyebang.model.Course;
import com.shg.keyebang.model.Todo;
import com.shg.keyebang.model.User;
import com.shg.keyebang.presenter.coursetable.CourseTablePresenter;
import com.shg.keyebang.view.activity.BaseFragment;
import com.shg.keyebang.view.activity.main.MainActivity;
import com.shg.keyebang.view.general.CircleImageView;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CourseTableFragment extends BaseFragment {
    private View view;
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


    public void setCourseTable(Map<Course, Todo> classTable){
        for(Course course: classTable.keySet()){
            CourseCard card = new CourseCard(getActivity());
            card.setCourse(course);
            card.setTodo(classTable.get(course));
            card.setLocation(course);
            card.setSize(course);
            card.setOnClickListener(v->{
                TodoDialog dialog = new TodoDialog((MainActivity)this.getActivity(), classTable.get(course), course);
                //dialog.setContentView(R.layout.dialog_todo);
                dialog.show();
            });
            courseCards.add(card);
            tableContainer.addView(card);
        }
        refreshLayout.setRefreshing(false);
    }


    public void setSemesterTime(String textTime) {
        semesterTime.setText(textTime);
    }
}
