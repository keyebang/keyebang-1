package com.shg.keyebang.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {
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
    public User setId4m3(String id4m3){
        this.id4m3=id4m3;
        return this;
    }
    public String getId4m3(){return id4m3;}
    public User setPassword4m3(String password4m3){
        this.password4m3=password4m3;
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

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    private String name;
    private String nickname;
    private String id4m3;
    private String password4m3;
    private String studentId;
    private String semester;
    private String sex;
}
