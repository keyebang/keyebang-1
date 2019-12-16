package com.shg.keyebang.view.activity.CourseList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.shg.keyebang.R;
import com.shg.keyebang.aatools.StringUtil;
import com.shg.keyebang.presenter.courselist.FindCoursePresenter;
import com.shg.keyebang.view.activity.BaseActivity;

public class SearchCourseActivity extends BaseActivity {
    private String searchValue;
    private FindCoursePresenter presenter;
    private EditText searchText;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
        presenter = new FindCoursePresenter(this);
        searchText = findViewById(R.id.searchText);
        search = findViewById(R.id.searchIcon);
        init();
    }

    @Override
    protected void init() {
        searchValue = getIntent().getStringExtra("searchValue" );
        searchText.setText(searchValue);
        presenter.getSearchResult(searchValue);
        search.setOnClickListener(v -> search());
        searchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event!=null && event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                search();
                return true;
            }
            return false;
        });
    }

    private void search(){
        String query = searchText.getText().toString();
        if(!StringUtil.isAllNullOrEmpty(query)){
            presenter.getSearchResult(query);
        }
        else toastAndLog("搜索信息为空");
    }
}
