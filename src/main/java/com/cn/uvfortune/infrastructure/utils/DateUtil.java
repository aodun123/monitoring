package com.cn.uvfortune.infrastructure.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: QHSE
 * @description: 时间转换工具类
 * @author: liuZhi
 * @create: 2018-07-05 17:19
 **/
public class DateUtil {
    /**
     * 字符转换时间，精确到日期
     *
     * @param date
     * @return
     */
    public static Date convertStringToDate(String date) {
        if (date == null || date.trim().equals("")) return null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符转换时间，精确到秒
     *
     * @param date
     * @return
     */
    public static Date convertStringToDateTime(String date) {
        if (date == null || date.trim().equals("")) return null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * util时间转换sql时间
     *
     * @param date
     * @return
     */
    public static java.sql.Date convertUtilToSql(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static String convertDayToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getPattenFromStr(String value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format_min = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format_sec = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.parse(value);
            return "yyyy-MM-dd";
        } catch (Exception e) {
            try {
                format_min.parse(value);
                return "yyyy-MM-dd HH:mm";
            } catch (Exception e1) {
                try {
                    format_sec.parse(value);
                    return "yyyy-MM-dd HH:mm:ss";
                } catch (Exception e2) {

                }
            }
        }
        return null;
    }
}
