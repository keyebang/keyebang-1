package com.shg.keyebang.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.account.SignUpActivity;
import com.shg.keyebang.view.activity.coursetable.CourseTableActivity;

public class MainActivity extends BaseActivity {
    private Button logOut;
    private Button toClassTable;
    private Button toTest;


    @Override
    protected void init() {
        logOut = findViewById(R.id.logout);
        toClassTable =findViewById(R.id.hello);
        toTest = findViewById(R.id.test);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        toTest.setOnClickListener((v)->{
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        logOut.setOnClickListener((v)->{
            //if(Account.isLogin()) Account.logOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        toClassTable.setOnClickListener((v)->{
            Intent intent = new Intent(this, CourseTableActivity.class);
            startActivity(intent);
        });

        User user = User.getCurrentUser(User.class);
        if(user != null) {
            toastAndLog(
                    "当前用户：" + "\n" +
                    user.getUsername() + "\n" +
                    user.getNickname() + "\n" +
                    user.getStudentId());
        }
        else toastAndLog("不存在当前用户，请尝试登录");
    }
}
