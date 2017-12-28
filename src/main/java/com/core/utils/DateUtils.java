package com.core.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;


public class DateUtils {
    /** 时间格式：yyyyMMddHHmmss'. */
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /** 时间格式：yyyyMMdd'. */
    public static String yyyyMMdd = "yyyyMMdd";

    /** 时间格式：yyyy-MM-dd'. */
    public static String yyyy_MM_dd = "yyyy-MM-dd";

    /** 时间格式：HH:mm:ss'. */
    public static String HH_mm_ss = "HH:mm:ss";
    
    /** 时间格式：yyyy-MM-dd' 'HH:mm:ss'. */
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd' 'HH:mm:ss";

    /** 时间格式：yyyy-MM-dd' 'HH:mm'. */
    public static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd' 'HH:mm";

    public static String formatDate(Date date) {
        if (date == null){
            return "";
        }
        return new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss).format(date);
    }
    
    public static String formatDate(Date date, String patten) {
        if (date == null){
            return "";
        }
        return new SimpleDateFormat(patten).format(date);
    }

    /**
     * <p>
     * 将一个日期字符串转换为日期类型对象（格式化日期字符串）
     * </p>
     * 
     * @param date
     *            日期字符串
     * @param dateFormat
     *            给定日期字符串的日期格式
     * @return
     * @throws ParseException
     */
    public static Date formatString(String date, String dateFormat) {
        if (date == null || date.length() <= 0) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date rs = null;
        try {
            rs = sdf.parse(date);
            return rs;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Date add(Date date, int field, int amount) {
        if (date == null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 添加年
     * 
     * @param curDate
     *            当前日期
     * @param val
     *            添加年数 如果为负值，则为减
     * @return
     */
    public static Date plusYears(Date curDate, int val) {
        DateTime dt = new DateTime(curDate.getTime());
        dt.plusYears(val);
        return dt.toDate();
    }

    /**
     * 添加月
     * 
     * @param curDate
     *            当前日期
     * @param val
     *            添加月数 如果为负值，则为减
     * @return
     */
    public static Date plusMonths(Date curDate, int val) {
        DateTime dt = new DateTime(curDate.getTime());
        dt.plusMonths(val);
        return dt.toDate();
    }

    /**
     * 添加天
     * 
     * @param curDate
     *            当前日期
     * @param val
     *            添加天数 如果为负值，则为减
     * @return
     */
    public static Date plusDays(Date curDate, int val) {
        DateTime dt = new DateTime(curDate.getTime());
        dt.plusDays(val);
        return dt.toDate();
    }

    /**
     * 添加小时
     * 
     * @param curDate
     *            当前日期
     * @param val
     *            添加小时数 如果为负值，则为减
     * @return
     */
    public static Date plusHours(Date curDate, int val) {
        DateTime dt = new DateTime(curDate.getTime());
        dt.plusHours(val);
        return dt.toDate();
    }

    /**
     * 添加分钟
     * 
     * @param curDate
     *            当前日期
     * @param val
     *            添加分钟数 如果为负值，则为减
     * @return
     */
    public static Date plusMinutes(Date curDate, int val) {
        DateTime dt = new DateTime(curDate.getTime());
        dt.plusMinutes(val);
        return dt.toDate();
    }

    /**
     * 时间字符串转对象
     * 
     * @param aMask
     *            模式
     * @param strDate
     *            时间
     * @return
     */
    public static final Date convertDate(String patten, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(patten);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        return date;
    }
    /**
     * 获取昨天
     * @return
     */
    public static final String getYesterday(){
    	 Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         calendar.add(Calendar.DAY_OF_MONTH, -1);
         return DateUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
    }
    
    /**
     * 获取今天
     * @return
     */
    public static final String getToday(){
    	return DateUtils.formatDate(new Date(), "yyyy-MM-dd");
    }
    
    
    /**
     * 获取明天
     * @return
     */
    public static final String getTomorrow(){
    	 Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         calendar.add(Calendar.DAY_OF_MONTH, 1);
         return DateUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
    }
}
