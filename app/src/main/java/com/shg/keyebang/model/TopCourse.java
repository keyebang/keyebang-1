package com.shg.keyebang.model;

import android.graphics.drawable.Drawable;

public class TopCourse extends ViewCourse {
    private Drawable img;

    public TopCourse(String className) {
        setCourseName(className);
    }

    public Drawable getImg() {
        return img;
    }

    public TopCourse setImg(Drawable img) {
        this.img = img;
        return this;
    }
}
