package com.shg.keyebang.view.activity.coursedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.shg.keyebang.R;
import com.shg.keyebang.aatools.DisplayUtil;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.model.ViewSecondHandBook;
import com.shg.keyebang.presenter.coursedetail.SecondHandBookPresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.coursedetail.adapter.SecondHandBookListAdapter;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

public class SecondHandBookActivity extends BaseActivity {
    private String evaId;
    private SecondHandBookPresenter presenter;
    private SecondHandBookListAdapter bookListAdapter;
    private LRecyclerViewAdapter lBookListAdapter;
    private LRecyclerView bookRecyclerView;
    private TitleBarLayout titleBar;
    private LinearLayout bookContainer;
    private ConstraintLayout addSecondHandBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_hand_book);
        evaId = getIntent().getStringExtra("evaId");
        presenter = new SecondHandBookPresenter(this);
        bookListAdapter = new SecondHandBookListAdapter();
        lBookListAdapter = new LRecyclerViewAdapter(bookListAdapter);
        bookRecyclerView = findViewById(R.id.secondHandBookRecycler);
        titleBar = findViewById(R.id.secondHandBookTitleBar);
        bookContainer = findViewById(R.id.secondHandBookContainer);
        addSecondHandBookButton = findViewById(R.id.addSecondHandButton);
        init();
    }

    @Override
    protected void init() {
        titleBar.setTitle("二手书信息");

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lBookListAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_course_item, bookContainer, false));
        bookRecyclerView.setOnRefreshListener(()->presenter.getSecondHandBookList(evaId));
        bookRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        bookRecyclerView.setLayoutManager(verticalLayoutManager);
        bookRecyclerView.setAdapter(lBookListAdapter);
        bookRecyclerView.setLoadMoreEnabled(false);

        addSecondHandBookButton.setOnClickListener(v->{
            final EditText edit = new EditText(this);
            edit.setWidth(DisplayUtil.dpTopx(200));
            edit.setTranslationX(DisplayUtil.dpTopx(20));
            edit.setBackground(null);
            edit.setHint("填写二手书信息");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(edit);
            builder.setTitle("添加书籍资料");
            builder.setPositiveButton("添加", (v1, i1)->{
                String bookName = edit.getText().toString();
                if(StringUtil.isAllNullOrEmpty(bookName)) toastAndLog("输入信息为空");
                else presenter.addSecondHandBook(bookName, evaId);
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getWindow().setLayout(DisplayUtil.dpTopx(360), LinearLayout.LayoutParams.WRAP_CONTENT);
        });

        presenter.getSecondHandBookList(evaId);
    }

    public void setSecondHandBookList(ArrayList<ViewSecondHandBook> books) {
        bookListAdapter.setBooks(books);
        lBookListAdapter.notifyDataSetChanged();
        bookRecyclerView.refreshComplete(0);
    }

    public void addSecondHandBookMessage(ViewSecondHandBook book){
        bookListAdapter.addSecondHandBook(book);
        lBookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
        bookRecyclerView.refreshComplete(0);
    }
}
