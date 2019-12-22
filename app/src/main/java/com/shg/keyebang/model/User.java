package com.shg.keyebang.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {
    private String name;
    private String nickname;
    private String studentId;
    private String semester;
    private String major;
    private String contactMessage;

    public User(){

    }

    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public User setStudentId(String studentId) {
        this.studentId = studentId;
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
}
