package com.shg.keyebang.model;

import android.graphics.drawable.Drawable;

import java.util.Calendar;

public class ViewComment {
    private String commentId;
    private Drawable avatar;
    private String commentUserName;
    private Calendar commentTime;
    private String commentMessage;

    public ViewComment(Drawable avatar, String commentUserName, Calendar commentTime, String commentMessage) {
        this.avatar = avatar;
        this.commentUserName = commentUserName;
        this.commentTime = commentTime;
        this.commentMessage = commentMessage;
    }

    public ViewComment() { }

    public static ViewComment builder(){
        return new ViewComment();
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public ViewComment setAvatar(Drawable avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public ViewComment setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
        return this;
    }

    public Calendar getCommentTime() {
        return commentTime;
    }

    public ViewComment setCommentTime(Calendar commentTime) {
        this.commentTime = commentTime;
        return this;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public ViewComment setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
        return this;
    }

    public String getCommentId() {
        return commentId;
    }

    public ViewComment setCommentId(String commentId) {
        this.commentId = commentId;
        return this;
    }
}
