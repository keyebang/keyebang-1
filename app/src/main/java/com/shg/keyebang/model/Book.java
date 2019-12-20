package com.shg.keyebang.model;

public class Book {
    private String bookName;

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
}
