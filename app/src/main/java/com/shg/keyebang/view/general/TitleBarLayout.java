package com.shg.keyebang.view.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shg.keyebang.R;
import com.shg.keyebang.model.User;
import com.shg.keyebang.view.activity.BaseActivity;

public class TitleBarLayout extends LinearLayout {
    private TextView title;
    private CircleImageView avatar;

    public TitleBarLayout(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        init();
    }

    public TitleBarLayout(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        init();
    }

    private void init(){
        title = findViewById(R.id.title);
        avatar = findViewById(R.id.avatar);
        avatar.setOnClickListener(v->{
            User user = User.getCurrentUser(User.class);
            if(user != null) {
                ((BaseActivity)getContext()).toastAndLog(
                        "当前用户：" + "\n" +
                                user.getUsername() + "\n" +
                                user.getNickname() + "\n");
            }
        });
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setAvatar(){

    }

}
