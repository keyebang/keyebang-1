package com.shg.keyebang.view.activity.main;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.view.activity.BaseActivity;
import com.shg.keyebang.view.activity.CourseList.CourseListFragment;
import com.shg.keyebang.view.activity.account.LoginActivity;
import com.shg.keyebang.view.activity.coursetable.CourseTableFragment;
import com.shg.keyebang.view.activity.profile.ProfileFragment;

import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity {
    private CourseTableFragment courseTableFragment;
    private CourseListFragment courseListFragment;
    private ProfileFragment profileFragment;
    private TabLayout bottomTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabLayout = findViewById(R.id.bottomTabLayout);
        courseListFragment = new CourseListFragment();
        courseTableFragment = new CourseTableFragment();
        profileFragment = new ProfileFragment();

        init();
    }

    @Override
    protected void init() {
        if(!User.isLogin()) {
            toastAndLog("请先登录");
            startActivityDirectly(LoginActivity.class);
            finish();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, courseTableFragment);
        fragmentTransaction.commit();

        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.ic_table_fill, null)));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.ic_school_black_24dp, null)));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.ic_person_black_24dp, null)));

        User user = User.getCurrentUser(User.class);
        if(user != null) {
            toastAndLog(
                    "当前用户：" + "\n" +
                            user.getUsername() + "\n" +
                            user.getNickname() + "\n");
        }
    }

    private void setFragment(int position){
        switch (position){
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, courseTableFragment).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, courseListFragment).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, profileFragment).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void showErrorMessage(String errMsg) {
        toastAndLog(errMsg);
    }
}
