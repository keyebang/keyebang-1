package com.shg.keyebang.services.account;

import com.shg.keyebang.model.User;
import com.shg.keyebang.services.coursedetail.AddDataListener;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
public class Account4m3 {

    public static void gatInfo(final User user, String password, String id4m3 , String password4m3, AddDataListener listener) {

        user.setPassword(password);
        //user.setId4m3(id4m3);
        //user.setPassword4m3(password4m3);
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
}
