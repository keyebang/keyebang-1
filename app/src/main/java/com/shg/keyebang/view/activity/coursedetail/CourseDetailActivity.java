package com.shg.keyebang.view.activity.coursedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewBook;
import com.shg.keyebang.model.ViewComment;
import com.shg.keyebang.model.ViewCourseInfo;
import com.shg.keyebang.model.ViewCourseSelect;
import com.shg.keyebang.presenter.coursedetail.CourseDetailPresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.coursedetail.adapter.CommentListAdapter;
import com.shg.keyebang.view.activity.coursedetail.adapter.CourseBookListAdapter;
import com.shg.keyebang.view.activity.coursedetail.adapter.CourseSelectListAdapter;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CourseDetailActivity extends BaseActivity {
    private String courseId;
    private String evaId;
    private String courseName;
    private int likeNum = 0;
    private boolean limit = false;
    private CourseDetailPresenter presenter;
    private CourseSelectListAdapter courseSelectListAdapter;
    private CourseBookListAdapter courseBookListAdapter;
    private CommentListAdapter commentListAdapter;
    private LRecyclerViewAdapter lCommentListAdapter;
    private RecyclerView courseTimeRecyclerView;
    private RecyclerView courseBookRecyclerView;
    private LRecyclerView commentRecyclerView;
    private LinearLayout courseDetailContainer;
    private SwipeRefreshLayout refreshLayout;
    private TitleBarLayout titleBar;
    private TextView likeNumText;
    private TextView courseType;
    private TextView courseCredit;
    private TextView courseMessage;
    private ImageView addBookButton;
    private ConstraintLayout getSecondHandBookButton;
    private TextView limitMessage;
    private EditText commentEditText;
    private ConstraintLayout sendComment;
    private LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        presenter = new CourseDetailPresenter(this);
        courseId = getIntent().getStringExtra("courseId");
        courseName = getIntent().getStringExtra("courseName" );
        courseSelectListAdapter = new CourseSelectListAdapter(this);
        courseBookListAdapter = new CourseBookListAdapter();
        commentListAdapter = new CommentListAdapter();
        lCommentListAdapter = new LRecyclerViewAdapter(commentListAdapter);
        titleBar = findViewById(R.id.detailTitleBar);
        courseTimeRecyclerView = findViewById(R.id.courseTimeRecycler);
        courseBookRecyclerView = findViewById(R.id.coursebookRecycler);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        courseDetailContainer = findViewById(R.id.courseDetailContainer);
        likeNumText = findViewById(R.id.likeNum);
        courseType = findViewById(R.id.courseType);
        courseCredit = findViewById(R.id.courseCredit);
        courseMessage = findViewById(R.id.courseMessage);
        addBookButton = findViewById(R.id.addBookButton);
        getSecondHandBookButton = findViewById(R.id.getSecondHandButton);
        refreshLayout = findViewById(R.id.refreshLayout);
        limitMessage = findViewById(R.id.limitMessage);
        commentEditText = findViewById(R.id.commentEditText);
        sendComment = findViewById(R.id.sendComment);
        likeButton = findViewById(R.id.likeButton);
        init();
    }

    @Override
    protected void init() {
        if(!StringUtil.isAllNullOrEmpty(courseName)) titleBar.setTitle(courseName);
        refreshLayout.setOnRefreshListener(this::getDataByEvaId);
        getSecondHandBookButton.setOnClickListener(v->{
            Intent intent = new Intent(this, SecondHandBookActivity.class);
            intent.putExtra("courseId", courseId);
            intent.putExtra("evaId", evaId);
            startActivity(intent);
        });
        addBookButton.setOnClickListener(v->{
            final EditText edit = new EditText(this);
            edit.setWidth(DisplayUtil.dpTopx(200));
            edit.setTranslationX(DisplayUtil.dpTopx(20));
            edit.setBackground(null);
            edit.setHint("填写书籍名称");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(edit);
            builder.setTitle("添加书籍资料");
            builder.setPositiveButton("添加", (v1, i1)->{
                String bookName = edit.getText().toString();
                if(StringUtil.isAllNullOrEmpty(bookName)) toastAndLog("输入信息为空");
                else presenter.addBook(bookName, evaId);
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getWindow().setLayout(DisplayUtil.dpTopx(360), LinearLayout.LayoutParams.WRAP_CONTENT);
        });
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                presenter.updateLike(evaId, 1);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                presenter.updateLike(evaId,-1);
            }
        });
        sendComment.setOnClickListener(v->presenter.sendComment(evaId, commentEditText.getText().toString()));

        LinearLayoutManager verticalTimeLayoutManager = new LinearLayoutManager(this);
        verticalTimeLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        courseTimeRecyclerView.setLayoutManager(verticalTimeLayoutManager);
        courseTimeRecyclerView.setAdapter(courseSelectListAdapter);

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

        getDataByEvaId();
    }

    private void getDataByEvaId(){
        //presenter.getLimit();
        presenter.getEvaId(courseId);
    }

    public void setEvaId(String evaId) {
        this.evaId = evaId;
        presenter.getLikeNum(evaId);
        presenter.getIsLike(evaId);
        presenter.getCourseInfo(courseId);
        presenter.getThisCourseList(evaId);
        presenter.getBookList(evaId);
        if(limit){
            presenter.getCommentList(evaId);
        }
        refreshLayout.setRefreshing(false);
    }

    public void setCourseInfoData(ViewCourseInfo courseInfo) {
        courseType.setText(courseInfo.getType());
        courseCredit.setText("学分：" + courseInfo.getCredit());
        courseMessage.setText(courseInfo.getInfo());
    }

    public void setTimeData(ArrayList<ViewCourseSelect> courseTimes){
        courseSelectListAdapter.setCourseSelects(courseTimes);
        courseSelectListAdapter.notifyDataSetChanged();
    }

    public void setBookData(ArrayList<ViewBook> books){
        courseBookListAdapter.setBooks(books);
        courseBookListAdapter.notifyDataSetChanged();
    }

    public void setCommentData(ArrayList<ViewComment> comments){
        commentListAdapter.setComments(comments);
        lCommentListAdapter.notifyDataSetChanged();
    }

    public void setLimit(boolean limit) {
        if( !this.limit && limit ){
            this.limit = true;
            presenter.getCommentList(evaId);
            limitMessage.setVisibility(View.GONE);
        }
        else if (!this.limit) limitMessage.setVisibility(View.VISIBLE);
        else limitMessage.setVisibility(View.GONE);
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
        likeNumText.setText(likeNum + "");
    }

    public void setIsLike(boolean isLike) {
        likeButton.setLiked(isLike);
    }

    public void updateLikeSuccess(int num) {
        likeNum += num;
        likeNumText.setText(likeNum + "");
    }

    public void addMyComment(ViewComment comment) {
        commentListAdapter.addComment(comment);
        lCommentListAdapter.notifyDataSetChanged();
        commentEditText.setText("");
        presenter.getLimit();
    }

    public void addCourseToTable(String courseId) {
        presenter.addCourseToTable(courseId);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        refreshLayout.setRefreshing(false);
    }
}
