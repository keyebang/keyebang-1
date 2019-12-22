package com.shg.keyebang.services.datamodel;


import androidx.annotation.NonNull;

import com.shg.keyebang.model.User;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import cn.bmob.v3.BmobObject;

public class Course extends BmobObject {
    private String className;
    private String classPlace;
    private String teacher;
    private String campus;
    private float credit;
    private int color;
    private User student;
    private Evaluation evaluationId;
    private String major;
    private String type;
    private String semester;


    public Course(){

    }

    public Course(String className, String classPlace, String teacher) {
        this.className = className;
        this.classPlace = classPlace;
        this.teacher = teacher;

    }

    public User getStudent(){
        return student;
    }

    public Course setStudent(User student){
        this.student=student;
        return this;
    }
  
    @NotNull
    @Contract(" -> new")
    public static Course builder(){
        return new Course();
    }

    public String getCampus() {
        return campus;
    }

    public Course setCampus(String campus) {
        this.campus = campus;
        return this;
    }

    public float getCredit() {
        return credit;
    }

    public Course setCredit(float credit) {
        this.credit = credit;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public Course setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public Course setClassPlace(String classPlace) {
        this.classPlace = classPlace;
        return this;
    }

    public String getTeacher() {
        return teacher;
    }

    public Course setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }


    public int getColor() {
        return color;
    }

    public Course setColor(@NonNull int color) {
        this.color = color;
        return this;
    }
    public Evaluation getEvaluationId(){return evaluationId;}
    public Course setEvaluationId(Evaluation evaluationId){
        this.evaluationId=evaluationId;
        return this;
    }
    public String getMajor(){return major;}
    public Course setMajor(String major){
        this.major=major;
        return this;
    }
    public String getType(){return type;}
    public Course setType(String type){
        this.type=type;
        return this;
    }
    public String getSemester(){return semester;}
    public Course setSemester(String semester){
        this.semester=semester;
        return this;
    }
}
