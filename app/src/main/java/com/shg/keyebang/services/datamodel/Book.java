package com.shg.keyebang.services.datamodel;

import cn.bmob.v3.BmobObject;

public class Book extends BmobObject {
    private String bookName;
    private String evaId;

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

    public String getEvaId(){return evaId;}
    public Book setEvaId(String evaId){
        this.evaId=evaId;
        return this;
    }
}
