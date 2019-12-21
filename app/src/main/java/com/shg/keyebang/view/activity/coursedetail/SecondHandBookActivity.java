package com.shg.keyebang.view.activity.coursedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.shg.keyebang.R;
import com.shg.keyebang.model.ViewSecondHandBook;
import com.shg.keyebang.presenter.coursedetail.SecondHandBookPresenter;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.coursedetail.adapter.SecondHandBookListAdapter;
import com.shg.keyebang.view.general.TitleBarLayout;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;

public class SecondHandBookActivity extends BaseActivity {
    private String evaId;
    private SecondHandBookPresenter presenter;
    private SecondHandBookListAdapter bookListAdapter;
    private LRecyclerViewAdapter lBookListAdapter;
    private LRecyclerView bookRecyclerView;
    private TitleBarLayout titleBar;
    private LinearLayout bookContainer;


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

        presenter.getSecondHandBookList(evaId);
    }

    public void setSecondHandBookList(ArrayList<ViewSecondHandBook> books) {
        bookListAdapter.setBooks(books);
        lBookListAdapter.notifyDataSetChanged();
        bookRecyclerView.refreshComplete(0);
    }
}
