package com.reaier.engking.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public final static String YmdHms = "yyyy-MM-dd HH:mm:ss";
    public final static String Ymd = "yyyy-MM-dd";
    public final static String Hms = "HH:mm:ss";

    public static String getYmdHms() {
        Calendar calendar = Calendar.getInstance();

        return new SimpleDateFormat(YmdHms).format(calendar.getTime());
    }

    public static String getYmdHms(Date date) {
        return new SimpleDateFormat(YmdHms).format(date);
    }

    public static String getYmdHms(java.sql.Date date) {
        return String.format("%s 00:00:00", getYmd(date));
    }

    public static String getYmd() {
        return new SimpleDateFormat(Ymd).format(new Date());
    }


    public static String getYmd(Date date) {
        return new SimpleDateFormat(Ymd).format(date);
    }

    public static String getYmd(java.sql.Date date) {
        return new SimpleDateFormat(Ymd).format(date);
    }

    public static String getHms() {
        return new SimpleDateFormat(Hms).format(new Date());
    }

    public static Date fromString(String ymdHms) {
        try {
            return new SimpleDateFormat(YmdHms).parse(ymdHms);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Date fromString(String ymd, String Hms) {
        try {
            return new SimpleDateFormat(YmdHms).parse(String.format("%s %s", ymd, Hms));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
