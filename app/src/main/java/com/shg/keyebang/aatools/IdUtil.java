package com.shg.keyebang.aatools;

public class IdUtil {
    public static String getCorrectId(String id){
        char c = id.charAt(0);
        if(!((48  <= c && c <= 57) || (65  <= c && c <= 90) || (97  <= c && c <= 122))){
            id = id.substring(1);
        }
        return id;
    }
}
