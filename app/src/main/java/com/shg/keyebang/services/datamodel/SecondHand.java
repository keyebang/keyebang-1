package com.shg.keyebang.services.datamodel;

import com.shg.keyebang.model.User;

import cn.bmob.v3.BmobObject;

public class SecondHand extends BmobObject {
    private String userId;
    private String evaId;
    private String bookMessage;
    private String contactMessage;

    public String getUserId(){
        return userId;
    }

    public SecondHand setUserId(String userId){
        this.userId=userId;
        return this;
    }

    public String getEvaId(){return evaId;}
    public SecondHand setEvaId(String evaId){
        this.evaId=evaId;
        return this;
    }

    public String getBookMessage(){return bookMessage;}
    public SecondHand setBookMessage(String bookMessage){
        this.bookMessage=bookMessage;
        return this;
    }
    public String getContactMessage(){return contactMessage;}
    public SecondHand setContactMessage(String contactMessage){
        this.contactMessage=contactMessage;
        return this;
    }
}
