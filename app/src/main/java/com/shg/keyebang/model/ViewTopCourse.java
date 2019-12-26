package com.shg.keyebang.model;

import android.graphics.drawable.Drawable;

public class ViewTopCourse extends ViewCourse {
    private Drawable img;

    public ViewTopCourse(String className) {
        setCourseName(className);
    }

    public Drawable getImg() {
        return img;
    }

    public ViewTopCourse setImg(Drawable img) {
        this.img = img;
        return this;
    }
}
