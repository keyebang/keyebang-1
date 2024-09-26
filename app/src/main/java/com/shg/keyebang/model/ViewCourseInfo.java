package com.shg.keyebang.model;

public class ViewCourseInfo {
    private String type;
    private float credit;
    private String info;

    public ViewCourseInfo(String type, float credit, String info) {
        this.type = type;
        this.credit = credit;
        this.info = info;
    }

    public ViewCourseInfo() {
    }

    public static ViewCourseInfo builder(){
        return new ViewCourseInfo();
    }

    public String getType() {
        return type;
    }

    public ViewCourseInfo setType(String type) {
        this.type = type;
        return this;
    }

    public float getCredit() {
        return credit;
    }

    public ViewCourseInfo setCredit(float credit) {
        this.credit = credit;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public ViewCourseInfo setInfo(String info) {
        this.info = info;
        return this;
    }
}
