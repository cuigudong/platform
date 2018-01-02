package com.xxx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sks on 2018/1/2.
 */
public class DateUtil {
    public final static String DEFAULT_PATTERN = "yyyyMMddHHmmss";
    public final static String FILEDATE_PATTERM = "yyyyMMdd";

    /**
     * 时间格式：yyyy-MM-dd' 'HH:mm:ss'.
     */
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd' 'HH:mm:ss";
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    private final static long TIMEMILLIS_OF_ONE_DAY = 1000 * 60 * 60 * 24;

    public static String formatDate(Date date, String patten) {
        if (date == null) return "";
        return new SimpleDateFormat(patten).format(date);
    }

    /**
     * 获取当前时间(格式:yyyyMMddHHmmss)
     *
     * @return
     */
    public static String getCurrDate() {
        return getCurrDate(DEFAULT_PATTERN);
    }

    /**
     * 字符串转日期，默认格式为yyyyMMddHHmmss
     *
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        return strToDate(str, DEFAULT_PATTERN);
    }

    /**
     * 日期格式化，默认格式为yyyyMMddHHmmss
     *
     * @param date 日期
     * @return
     */
    public static String format(Date date) {
        return DateUtil.format(date, DEFAULT_PATTERN);
    }

    /**
     * 字符串转日期
     *
     * @param str     时间
     * @param pattern 格式
     * @return
     */
    public static Date strToDate(String str, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期格式化
     *
     * @param date    日期
     * @param pattern 格式
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取当前时间
     *
     * @param pattern 格式
     * @return
     */
    public static String getCurrDate(String pattern) {
        long d = System.currentTimeMillis();
        Date date = new Date(d);

        return format(date, pattern);
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Date getSystemDate() {
        long d = System.currentTimeMillis();
        Date date = new Date(d);

        return date;
    }

    /**
     * 时间字符串转对象
     *
     * @param
     * @param strDate 时间
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
     * 格式化时间
     *
     * @param strTime
     * @param pattern
     * @return
     */
    public static String getTime(String strTime, String pattern) {
        SimpleDateFormat s = new SimpleDateFormat(pattern);
        try {
            return String.valueOf(s.parse(strTime).getTime());// 时间戳
        } catch (ParseException e) {
            e.printStackTrace();
            return String.valueOf(new Date().getTime());
        }
    }

    /**
     * 格式化日期格式
     *
     * @param date
     * @return
     */
    public static final String date2string(Date date, String style) {
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        return sdf.format(date);
    }

    /**
     * 获取本地日期时间.
     *
     * @return 本地日期时间yyyyMMddHHmmss
     */
    public static String getLocalFullDateTime14() {
        return date2string(new Date(), DEFAULT_PATTERN);
    }


    /**
     * 返回指定格式时间
     *
     * @param mask
     * @return
     */
    public static final String now2string(String mask) {
        return date2string(new Date(), mask);
    }

    /**
     * @param date
     * @param field
     * @param amount
     * @return 对指定的日期做加减运算；<br>
     * 减：add(new Date(), Calendar.DATE, -1)返回昨天的日期<br>
     * 加：add(new Date(), Calendar.YEAR, 1)返回一年后的今天
     */
    public static Date add(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 增加指定日期,此方法目前只能用于DAY
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date addWorkDay(Date date, int field, int amount) {
        if (amount < 0 || field != Calendar.DATE) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (amount == 0) {
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                c.add(Calendar.DATE, 2);
            } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                c.add(Calendar.DATE, 1);
            }
        }
        for (int i = 0; i < amount; i++) {
            c.add(Calendar.DATE, 1);
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                i--;
            }
        }
        /**if (c.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY ||
         c.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY) {
         c.setTime(addWorkDay(c.getTime(), Calendar.DATE,1));
         }**/
        return c.getTime();
    }


    /**
     * 返回需要格式的字符串时间
     *
     * @param str      字符串时间
     * @param patternS 字符串格式
     * @param patternE 格式化后的格式
     * @return
     */
    public static String strToStr(String str, String patternS, String patternE) {

        return format(strToDate(str, patternS), patternE);

    }

    /**
     * 获取昨天的日期yyyyMMdd   guomuye
     */
    @SuppressWarnings("static-access")
    public static String getYesterday() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取本月第一天日期
     *
     * @return
     */
    public static Date getMonthFistDay() {
        Date date = new Date();//取时间
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        date = cal_1.getTime();
        return date;
    }

    /**
     * 返回指定日期之间的间隔天数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 间隔天数
     */
    public static int daysBetween(Date start, Date end) {
        long m = end.getTime() - start.getTime();
        return (int) (m / TIMEMILLIS_OF_ONE_DAY);
    }

    /**
     * 是否跨自然日
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return
     */
    public static boolean differentDays(Date start, Date end) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        return year1 != year2 ? true : day2 > day1;
    }

    public static int getCurrHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 根据生日计算年龄
     *
     * @param birthday 生日
     * @return 年龄(如果生日参数大于当前日期, 则返回-1)
     * @author junjie.ge
     */
    public static int getAge(Date birthday) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            return -1;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth - 1;
        if (monthBirth < monthNow || (monthBirth == monthNow && dayOfMonthBirth <= dayOfMonthNow)) {
            age++;
        }
        return age;
    }


    /**
     * 获得当天的开始时间
     *
     * @return
     */
    public static String getStartTimeOfDay() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return format(todayStart.getTime());
    }

    /**
     * 获得当天的结束时间
     *
     * @return
     */
    public static String getEndTimeOfDay() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return format(todayEnd.getTime());
    }

    public static String getStartTimeOfMonth() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.DAY_OF_MONTH, 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return format(todayStart.getTime());
    }

    public static String getEndTimeOfMonth() {
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.MILLISECOND, 999);
        Date d = new Date(ca.getTimeInMillis());
        return format(d);
    }

    /**
     * 获得当天的开始时间
     *
     * @return
     */
    public static Date getStartOfDay() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获得当天的结束时间
     *
     * @return
     */
    public static Date getEndOfDay() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * @return
     * @Description:
     * @author
     */
    public static String getCurrentYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    /**
     * @return
     * @Description:
     * @author
     */
    public static int getCurrentMonth() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.MONTH) + 1;
    }

    public static void main(String[] args) {
        System.out.println(getCurrentMonth());

    }
}
