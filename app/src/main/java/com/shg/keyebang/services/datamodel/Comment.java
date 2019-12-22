package com.shg.keyebang.services.datamodel;

import android.graphics.drawable.Drawable;

import com.shg.keyebang.model.User;

import java.util.Calendar;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {
    private Drawable avatar;
    private String commentUserName;
    private Calendar commentTime;
    private String commentMessage;
    private String userId;
    private String evaId;


    public Comment(Drawable avatar, String commentUserName, Calendar commentTime, String commentMessage) {
        this.avatar = avatar;
        this.commentUserName = commentUserName;
        this.commentTime = commentTime;
        this.commentMessage = commentMessage;
    }

    public Comment() {
    }

    public static Comment builder(){ return new Comment(); }

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

    public String getUserId(){
        return userId;
    }
    public Comment setUserId(String userId){
        this.userId=userId;
        return this;
    }

    public String getEvaId(){return evaId;}
    public Comment setEvaId(String evaId){
        this.evaId=evaId;
        return this;
    }
}
