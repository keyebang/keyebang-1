package com.shg.keyebang.aatools;

import android.text.TextUtils;

public class StringUtil {
    public static boolean isNullOrEmpty(String... strings){
        for (String string : strings) {
            if(TextUtils.isEmpty(string)) return true;
        }
        return false;
    }
}
