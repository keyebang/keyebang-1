package com.shg.keyebang.services.account;

import com.shg.keyebang.model.User;
import com.shg.keyebang.services.coursedetail.AddDataListener;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class AccountInfoService {

    public static void setInfo(final User user, AddDataListener listener) {

        if(user.getSemester()=="大一上"){user.setLimit(true);}
        else{user.setLimit(false);}

        user.update(new UpdateListener(){
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

    public static void updateInfo(final User user,AddDataListener listener){
        user.update(new UpdateListener(){
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

    public static void getInfo(GetInfoListener listener){

        BmobQuery<User> query=new BmobQuery<>();
        query.addWhereEqualTo("objectId", BmobUser.getCurrentUser(User.class));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if(e==null){
                    for (User user:object){
                        listener.onSuccess(user);
                    }
                }else{listener.onFailure("创建数据失败：" + e.getMessage());}


            }
        });
    }


}
