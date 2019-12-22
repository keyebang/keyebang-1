package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewSecondHandBook;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.view.activity.coursedetail.SecondHandBookActivity;

import java.util.ArrayList;

public class SecondHandBookPresenter extends BasePresenter {
    private SecondHandBookActivity activity;


    public SecondHandBookPresenter(SecondHandBookActivity activity) {
        this.activity = activity;
    }

    public void getSecondHandBookList(String evaId){
        ViewSecondHandBook book = ViewSecondHandBook.builder()
                .setBookMessage("二手：《他改变了中国》，15元")
                .setContactMessage(User.getCurrentUser(User.class).getNickname() + "  " + User.getCurrentUser(User.class).getContactMessage());
        ArrayList<ViewSecondHandBook> books = new ArrayList<>();
        books.add(book);
        books.add(book);
        activity.setSecondHandBookList(books);
    }

    public void addSecondHandBook(String bookName, String evaId){
        //....
        ViewSecondHandBook book = ViewSecondHandBook.builder()
                .setBookMessage(bookName)
                .setContactMessage(User.getCurrentUser(User.class).getNickname() + "  " + User.getCurrentUser(User.class).getContactMessage());
        activity.addSecondHandBookMessage(book);

    }
}
