package com.shg.keyebang.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {
    private String nickname;
    private String semester;
    private String major;
    private String contactMessage;
    private boolean limit;

    public User(){

    }

    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getSemester() {
        return semester;
    }

    public User setSemester(String semester) {
        this.semester = semester;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public User setMajor(String major) {
        this.major = major;
        return this;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public User setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
        return this;
    }

    public boolean isLimit() {
        return limit;
    }

    public User setLimit(boolean limit) {
        this.limit = limit;
        return this;
    }
}
