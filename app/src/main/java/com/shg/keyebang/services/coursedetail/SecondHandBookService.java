package com.shg.keyebang.services.coursedetail;

import com.shg.keyebang.model.User;
import com.shg.keyebang.model.ViewSecondHandBook;
import com.shg.keyebang.services.datamodel.Evaluation;
import com.shg.keyebang.services.datamodel.SecondHand;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class SecondHandBookService {

    public static void getSecondHandBookList(String evaId,GetSecondHandBookListListener listener){
        BmobQuery<SecondHand> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("evaId",evaId);
        query1.setLimit(100);
        query1.findObjects(new FindListener<SecondHand>() {
            @Override
            public void done(List<SecondHand> object, BmobException e) {
                if(e==null){
                    ArrayList<ViewSecondHandBook> books = new ArrayList<>();
                    for(SecondHand secondHand:object) {
                        ViewSecondHandBook book = ViewSecondHandBook.builder()
                                .setBookMessage(secondHand.getBookMessage())
                                .setContactMessage(secondHand.getContactMessage());
                        books.add(book);
                    }
                    listener.onSuccess(books);
                }
                else{listener.onFailure("查询失败"+e.getMessage()+e.getErrorCode());}

            }
        });
    }

    public static void addSecondHandBook(String evaId,String message,AddDataListener listener){
        Evaluation evaluation = new Evaluation();
        evaluation.setObjectId(evaId);
        final SecondHand secondHand =new SecondHand();
        secondHand.setEvaId(evaluation);
        secondHand.setBookMessage(message);
        secondHand.setContactMessage(User.getCurrentUser(User.class).getNickname() + "  " + User.getCurrentUser(User.class).getContactMessage());
        secondHand.update(new UpdateListener(){
            @Override
            public void done(BmobException e) {
                if(e==null){
                    listener.onSuccess("添加数据成功");
                }else{
                    listener.onFailure("创建数据失败：" + e.getMessage());
                }
            }
        });

    }
}
