package com.shg.keyebang.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Calendar;

public class Comment {
    private Drawable avatar;
    private String commentUserName;
    private Calendar commentTime;
    private String commentMessage;

    public Comment(Drawable avatar, String commentUserName, Calendar commentTime, String commentMessage) {
        this.avatar = avatar;
        this.commentUserName = commentUserName;
        this.commentTime = commentTime;
        this.commentMessage = commentMessage;
    }

    public Comment() {
    }

    public static Comment builder(){
        return new Comment();
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public Comment setAvatar(Drawable avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public Comment setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
        return this;
    }

    public Calendar getCommentTime() {
        return commentTime;
    }

    public Comment setCommentTime(Calendar commentTime) {
        this.commentTime = commentTime;
        return this;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public Comment setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
        return this;
    }
}
