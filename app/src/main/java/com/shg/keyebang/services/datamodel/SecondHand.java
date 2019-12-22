package com.shg.keyebang.services.datamodel;

import com.shg.keyebang.model.User;

import cn.bmob.v3.BmobObject;

public class SecondHand extends BmobObject {
    private User userId;
    private Evaluation evaId;

    public User getUserId(){
        return userId;
    }

    public SecondHand setUserId(User userId){
        this.userId=userId;
        return this;
    }

    public Evaluation getEvaId(){return evaId;}
    public SecondHand setEvaId(Evaluation evaId){
        this.evaId=evaId;
        return this;
    }
}
