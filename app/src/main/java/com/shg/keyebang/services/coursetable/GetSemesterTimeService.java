package com.shg.keyebang.services.coursetable;

import java.util.Calendar;

public class GetSemesterTimeService {
    public static void getSemesterTime(GetSemesterTimeListener listener){

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.set(2019,9,2,0,0,0);		
        Calendar endCalendar = Calendar.getInstance();

        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 *24));
        int num=(betweenDays/7)+6;

        listener.onSuccess(num);



    }
}
