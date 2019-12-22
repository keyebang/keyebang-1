package com.shg.keyebang.services.datamodel;

import cn.bmob.v3.BmobObject;

public class Evaluation extends BmobObject {

    private String content;
    public Evaluation(String content){
        this.content=content;
    }

    public Evaluation setEvaluation(String content){
        this.content=content;
        return this;
    }
    public String getContent(){return content;}

}
