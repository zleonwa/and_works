package com.example.dami.myweather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConvertDate {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00");
    public static String TO_GMT(long time){
        Date d = new Date();
        d.setTime(time*1000);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return sdf.format(d);
    }
}
