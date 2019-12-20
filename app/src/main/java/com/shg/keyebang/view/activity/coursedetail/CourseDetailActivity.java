package com.shg.keyebang.view.activity.coursedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.Book;
import com.shg.keyebang.model.Comment;
import com.shg.keyebang.model.CourseTime;
import com.shg.keyebang.presenter.coursedetail.CourseDetailPresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.coursedetail.adapter.CommentListAdapter;
import com.shg.keyebang.view.activity.coursedetail.adapter.CourseBookListAdapter;
import com.shg.keyebang.view.activity.coursedetail.adapter.CourseTimeListAdapter;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CourseDetailActivity extends BaseActivity {
    private String courseName;
    private String courseTeacher;
    private CourseDetailPresenter presenter;
    private CourseTimeListAdapter courseTimeListAdapter;
    private CourseBookListAdapter courseBookListAdapter;
    private CommentListAdapter commentListAdapter;
    private LRecyclerViewAdapter lCommentListAdapter;
    private RecyclerView courseTimeRecyclerView;
    private RecyclerView courseBookRecyclerView;
    private LRecyclerView commentRecyclerView;
    private LinearLayout courseDetailContainer;
    private SwipeRefreshLayout refreshLayout;
    private TitleBarLayout titleBar;
    private EditText commentEdittext;
    private ConstraintLayout sendComment;
    private LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        presenter = new CourseDetailPresenter(this);
        courseName = getIntent().getStringExtra("courseName" );
        courseTeacher = getIntent().getStringExtra("courseTeacher" );
        titleBar = findViewById(R.id.detailTitleBar);
        courseTimeListAdapter = new CourseTimeListAdapter();
        courseBookListAdapter = new CourseBookListAdapter();
        commentListAdapter = new CommentListAdapter();
        lCommentListAdapter = new LRecyclerViewAdapter(commentListAdapter);
        courseTimeRecyclerView = findViewById(R.id.courseTimeRecycler);
        courseBookRecyclerView = findViewById(R.id.coursebookRecycler);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        courseDetailContainer = findViewById(R.id.courseDetailContainer);
        refreshLayout = findViewById(R.id.refreshLayout);
        commentEdittext = findViewById(R.id.commentEditText);
        sendComment = findViewById(R.id.sendComment);
        likeButton = findViewById(R.id.likeButton);
        init();
    }

    @Override
    protected void init() {
        if(!StringUtil.isAllNullOrEmpty(courseName)) titleBar.setTitle(courseName);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });
        sendComment.setOnClickListener(v->presenter.sendComment(commentEdittext.getText().toString()));

        LinearLayoutManager verticalTimeLayoutManager = new LinearLayoutManager(this);
        verticalTimeLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        courseTimeRecyclerView.setLayoutManager(verticalTimeLayoutManager);
        courseTimeRecyclerView.setAdapter(courseTimeListAdapter);

        LinearLayoutManager verticalBookLayoutManager = new LinearLayoutManager(this);
        verticalBookLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        courseBookRecyclerView.setLayoutManager(verticalBookLayoutManager);
        courseBookRecyclerView.setAdapter(courseBookListAdapter);

        LinearLayoutManager verticalCommentLayoutManager = new LinearLayoutManager(this);
        verticalCommentLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lCommentListAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_course_item, courseDetailContainer, false));
        commentRecyclerView.setPullRefreshEnabled(false);
        commentRecyclerView.setLayoutManager(verticalCommentLayoutManager);
        commentRecyclerView.setAdapter(lCommentListAdapter);
        commentRecyclerView.setLoadMoreEnabled(false);

        getData();
    }

    private void getData(){
        presenter.getCourseTimeList();
        presenter.getBookList();
        presenter.getCommentList();
        refreshLayout.setRefreshing(false);
    }

    public void setTimeData(ArrayList<ArrayList<CourseTime>> courseTimes){
        courseTimeListAdapter.setCourseTimes(courseTimes);
        courseTimeListAdapter.notifyDataSetChanged();
    }

    public void setBookData(ArrayList<Book> books){
        courseBookListAdapter.setBooks(books);
        courseBookListAdapter.notifyDataSetChanged();
    }

    public void setCommentData(ArrayList<Comment> comments){
        commentListAdapter.setComments(comments);
        lCommentListAdapter.notifyDataSetChanged();
    }

    public void addMyComment(Comment comment) {
        commentListAdapter.addComment(comment);
        lCommentListAdapter.notifyDataSetChanged();
        commentEdittext.setText("");
    }
}
