package com.picspacehd.picspacehd.mangazone.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static String TIME_STAMP_TO_DATE(Long timestamp) {
        Date date = new Date(timestamp*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        return sdf.format(date);
    }
}
