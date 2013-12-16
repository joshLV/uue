package com.zihexin.business_interface.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.MissingResourceException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 *
 */
public class DateUtils {
    public static final String defaultDatePattern = "yyyy-MM-dd";
    
    public static final String defaultDatePattern2 = "yyyyMMdd";
    
    public static final String FullDatePattern = "yyyy-MM-dd HH:mm:ss";

    public static final String FullDatePattern2 = "yyyyMMddHHmmss";

    public static final String HFDatePattern = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy";

    static {
        // 尝试试从messages_zh_Cn.properties中获取defaultDatePattner.
        try {
            // Locale locale = LocaleContextHolder.getLocale();
            // defaultDatePattern =
            // ResourceBundle.getBundle(Constants.MESSAGE_BUNDLE_KEY,
            // locale).getString("date.default_format");
        	
        } catch (MissingResourceException mse) {
            // do nothing
        }
    }

    /**
     * 获得默认�?date pattern
     *
     * @return String
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }
    
    
    /**
     * 返回预设Format的当前日期字符串
     *
     * @return String
     */
    public static String getToday() {
        return format(now());
    }

    /**
     * 返回当前时间
     *
     * @return Date实例
     */
    public static Date now() {
        return nowCal().getTime();
    }

    /**
     * 当前时间
     *
     * @return Calendar实例
     */
    public static Calendar nowCal() {
        return Calendar.getInstance();
    }

    /**
     * Date型转化到Calendar�?
     *
     * @param date
     * @return Calendar
     */
    public static Calendar date2Cal(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 当前时间的下�?��
     *
     * @return Calendar
     */
    public static Calendar nextDay() {
        return nextDay(nowCal());
    }

    /**
     * 当前时间的下�?��
     *
     * @return Calendar
     */
    public static Calendar nextMonth() {
        return nextMonth(nowCal());
    }

    /**
     * 当前时间的下�?��
     *
     * @return Calendar
     */
    public static Calendar nextYear() {
        return nextMonth(nowCal());
    }

    /**
     * 下一�?
     *
     * @param cal
     * @return Calendar
     */
    public static Calendar nextDay(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return afterDays(cal, 1);
    }

    /**
     * 下一�?
     *
     * @param cal
     * @return Calendar
     */
    public static Calendar nextMonth(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return afterMonths(cal, 1);
    }

    /**
     * 下一�?
     *
     * @param cal
     * @return Calendar
     */
    public static Calendar nextYear(Calendar cal) {
        if (cal == null) {
            return null;
        }
        return afterYesrs(cal, 1);
    }

    /**
     * 后n�?
     *
     * @param cal
     * @param n
     * @return Calendar
     */
    public static Calendar afterDays(Calendar cal, int n) {
        if (cal == null) {
            return null;
        }
        Calendar c = (Calendar) cal.clone();
        c.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + n);
        return c;
    }

    /**
     * 下N�?
     *
     * @param cal
     * @param n
     * @return
     */
    public static Calendar afterSecond(Calendar cal, int n) {
        if (cal == null) {
            return null;
        }
        Calendar c = (Calendar) cal.clone();
        c.set(Calendar.SECOND, cal.get(Calendar.SECOND) + n);
        return c;
    }

    /**
     * 后n�?
     *
     * @param cal
     * @param n
     * @return Calendar
     */
    public static Calendar afterMonths(Calendar cal, int n) {
        if (cal == null) {
            return null;
        }
        Calendar c = (Calendar) cal.clone();
        c.set(Calendar.MONTH, cal.get(Calendar.MONTH) + n);
        return c;
    }

    /**
     * 后n�?
     *
     * @param cal
     * @param n
     * @return Calendar
     */
    public static Calendar afterYesrs(Calendar cal, int n) {
        if (cal == null) {
            return null;
        }
        Calendar c = (Calendar) cal.clone();
        c.set(Calendar.YEAR, cal.get(Calendar.YEAR) + n);
        return c;
    }

    /**
     * 使用预设Format格式化Date成字符串
     *
     * @return String
     */
    public static String format(Date date) {
        return date == null ? "" : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串
     *
     * @return String
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 试用参数Format格式化Calendar成字符串
     *
     * @param cal
     * @param pattern
     * @return String
     */
    public static String format(Calendar cal, String pattern) {
        return cal == null ? "" : new SimpleDateFormat(pattern).format(cal
                .getTime());
    }



    /**
     * 在日期上增加数个整月
     *
     * @return Date
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * get String value(MM/dd/yyyy) of time
     *
     * @param d
     * @return String
     */
    public static String dateToString(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat lenientDateFormat = new SimpleDateFormat(FullDatePattern);
        return lenientDateFormat.format(d);
    }


    /**
     * get String value(MM/dd/yyyy) of time
     *
     * @param d
     * @return String
     */
    public static String dateToString2(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat lenientDateFormat = new SimpleDateFormat(FullDatePattern2);
        return lenientDateFormat.format(d);
    }

    /**
     * @param date  某一�?
     * @param day  加多少天�?
     * @param formatText 返回日期格式
     * @return  返回日期
     * @author JZH
     * Mar 22, 2012 2:35:16 PM�?
     */
    public static String addDay(String date, int day, String formatText) {
        if (date == null || "".equals(date)) {
            return null;
        }

        if(formatText == null || "".equals(formatText)) {
            formatText = "yyyy-MM-dd";
        }

        SimpleDateFormat myFormatter = new SimpleDateFormat(formatText);

        try {
            Date paseDate = myFormatter.parse(date);
            long dayMill = 24 * 60 * 60 * 1000;
            long dayValue = paseDate.getTime() + dayMill*day;
            return myFormatter.format(dayValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getRequestDate() {
        Date requestDate = new Date();//请求时间
        return requestDate;
    }


    public static Date getStrDate(String date) {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dt = null;
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;

    }

    public static Date getStrDateFormat2(String date) {

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        Date dt = null;
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;

    }

    public static Date getStrDateFormat(String date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = null;
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;

    }

    public static void main(String args[]) throws ParseException {
        System.out.println (DateUtils.format(new Date(),"HHmmss"));
    }
}
