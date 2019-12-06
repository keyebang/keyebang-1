package com.shg.keyebang.aatools;

public class MyTools {

    public static String getGreeting(int i) {
        if(i < 6 || i >= 18) return "晚上好";
        if(6 <= i  && i < 12) return "早上好";
        return "下午好";
    }

    public static String numToCN(int i){
        switch (i){
            case 1: return "一";
            case 2: return "二";
            case 3: return "三";
            case 4: return "四";
            case 5: return "五";
            case 6: return "六";
            case 7: return "日";
        }
        return i + "";
    }
}
