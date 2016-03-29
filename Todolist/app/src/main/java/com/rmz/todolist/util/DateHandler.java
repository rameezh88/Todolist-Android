package com.rmz.todolist.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rameezh88 on 23/11/15.
 */
public class DateHandler {
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DATE_FORMAT, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date getDateFromString (String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
