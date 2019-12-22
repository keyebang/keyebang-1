package com.shg.keyebang.presenter.coursedetail;

import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewSecondHandBook;
import com.shg.keyebang.presenter.BasePresenter;
import com.shg.keyebang.services.coursedetail.AddDataListener;
import com.shg.keyebang.services.coursedetail.GetSecondHandBookListListener;
import com.shg.keyebang.services.coursedetail.SecondHandBookService;
import com.shg.keyebang.services.datamodel.SecondHand;
import com.shg.keyebang.view.activity.coursedetail.SecondHandBookActivity;

import java.util.ArrayList;

public class SecondHandBookPresenter extends BasePresenter {
    private SecondHandBookActivity activity;


    public SecondHandBookPresenter(SecondHandBookActivity activity) {
        this.activity = activity;
    }

    public void getSecondHandBookList(String evaId){
        SecondHandBookService.getSecondHandBookList(evaId, new GetSecondHandBookListListener() {
            @Override
            public void onSuccess(ArrayList<ViewSecondHandBook> books) {
                activity.setSecondHandBookList(books);
            }

            @Override
            public void onFailure(String errMassage) {

            }
        });


    }

    public void addSecondHandBook(String bookName, String evaId){
        //....
        SecondHandBookService.addSecondHandBook(evaId, bookName, new AddDataListener() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onFailure(String errMassage) {

            }
        });
        ViewSecondHandBook book = ViewSecondHandBook.builder()
                .setBookMessage(bookName)
                .setContactMessage(User.getCurrentUser(User.class).getNickname() + "  " + User.getCurrentUser(User.class).getContactMessage());
        activity.addSecondHandBookMessage(book);

    }
}
