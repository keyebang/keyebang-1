package com.shg.keyebang.aatools;

import android.text.TextUtils;

public class StringUtil {
    public static boolean isSomeNullOrEmpty(String... strings){
        for (String string : strings) {
            if(TextUtils.isEmpty(string)) return true;
        }
        return false;
    }

    public static boolean isAllNullOrEmpty(String... strings){
        for (String string : strings) {
            if(!TextUtils.isEmpty(string)) return false;
        }
        return true;
    }
}
