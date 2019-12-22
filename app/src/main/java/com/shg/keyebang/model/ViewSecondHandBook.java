package com.shg.keyebang.model;

public class ViewSecondHandBook {
    private String secondHandMessageId;
    private String contactMessage;
    private String bookMessage;

    public ViewSecondHandBook() {
    }

    public ViewSecondHandBook(String secondHandMessageId, String contactMessage, String bookMessage) {
        this.secondHandMessageId = secondHandMessageId;
        this.contactMessage = contactMessage;
        this.bookMessage = bookMessage;
    }

    public static ViewSecondHandBook builder(){
        return new ViewSecondHandBook();
    }


    public String getSecondHandMessageId() {
        return secondHandMessageId;
    }

    public ViewSecondHandBook setSecondHandMessageId(String secondHandMessageId) {
        this.secondHandMessageId = secondHandMessageId;
        return this;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public ViewSecondHandBook setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
        return this;
    }

    public String getBookMessage() {
        return bookMessage;
    }

    public ViewSecondHandBook setBookMessage(String bookMessage) {
        this.bookMessage = bookMessage;
        return this;
    }
}
