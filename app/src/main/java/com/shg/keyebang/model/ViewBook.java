package com.shg.keyebang.model;

public class ViewBook {
    private String bookId;
    private String bookName;

    public ViewBook(){ }

    public ViewBook(String bookName) {
        this.bookName = bookName;
    }

    public static ViewBook builder(){
        return new ViewBook();
    }

    public String getBookName() {
        return bookName;
    }

    public ViewBook setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getBookId() {
        return bookId;
    }

    public ViewBook setBookId(String bookId) {
        this.bookId = bookId;
        return this;
    }
}
