package com.shg.keyebang.services.datamodel;

import cn.bmob.v3.BmobObject;

public class Book extends BmobObject {
    private String bookName;
    private Evaluation evaId;

    public Book(){

    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public static Book builder(){
        return new Book();
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Evaluation getEvaId(){return evaId;}
    public Book setEvaId(Evaluation evaId){
        this.evaId=evaId;
        return this;
    }
}
