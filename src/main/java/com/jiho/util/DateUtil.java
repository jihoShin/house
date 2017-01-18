package com.jiho.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiho87.shin on 2017-01-12.
 */
public class DateUtil {


    public static Date getDate(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 15);
        return cal.getTime();
    }


    public static Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date redate = null;
        redate = formatter.parse(date);
        return redate;
    }


}
