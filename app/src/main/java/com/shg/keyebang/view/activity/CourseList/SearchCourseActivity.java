package com.shg.keyebang.view.activity.CourseList;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.shg.keyebang.R;
import com.shg.keyebang.view.activity.BaseActivity;

public class SearchCourseActivity extends BaseActivity {
    private String searchValue;
    private EditText searchText;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
        searchText = findViewById(R.id.searchText);
        search = findViewById(R.id.searchIcon);
        init();
    }

    @Override
    protected void init() {
        searchValue = getIntent().getStringExtra("searchValue" );
        searchText.setText(searchValue);
    }
}
