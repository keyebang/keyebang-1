package com.shg.keyebang.services.datamodel;

import cn.bmob.v3.BmobObject;

public class Likes extends BmobObject {
    private String userId;
    private String evaId;
    private boolean like;

    public Likes(){}

    public String getEvaId() {
        return evaId;
    }

    public Likes setEvaId(String evaId) {
        this.evaId = evaId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Likes setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public boolean isLike() {
        return like;
    }

    public Likes setLike(boolean like) {
        this.like = like;
        return this;
    }
}
