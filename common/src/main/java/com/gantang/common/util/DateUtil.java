package com.gantang.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtil {
    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 将数字型换成短中文显示格式，格式为"YYYY年MM月DD日"
     *
     * @param str
     * @return
     */
    public static String intToShortCn(Long str) {
        Date d = new Date(str);
        return DateUtil.format(d, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        final ParsePosition pos = new ParsePosition(8);
        return formatter.parse(dateString, pos);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final String dateString = formatter.format(currentTime);
        final ParsePosition pos = new ParsePosition(8);
        return formatter.parse(dateString, pos);
    }

    /**
     * 获取现在时间
     *
     * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(currentTime);
    }

    /**
     * 获取时间 HH:mm:ss
     *
     * @return 时间
     */
    public static String getTimeShort() {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date currentTime = new Date();
        return formatter.format(currentTime);
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate strDate
     * @return 时间
     */
    public static Date strToDateLong(final String strDate) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate dateDate
     * @return 时间
     */
    public static String dateToStrLong(final Date dateDate) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateDate);
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate dateDate
     * @return 时间
     */
    public static String dateToStr(final Date dateDate) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(dateDate);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate strDate
     * @return 时间
     */
    public static Date strToDate(final String strDate) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 得到现在时间
     *
     * @return 时间
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day day
     * @return 时间
     */
    public static Date getLastDate(final long day) {
        final Date date = new Date();
        final long date_3_hm = date.getTime() - 3600000 * 34 * day;
        return new Date(date_3_hm);
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        return formatter.format(currentTime);
    }

    /**
     * 得到现在小时
     */
    public static String getHour() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        final String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     *
     * @return 时间
     */
    public static String getTime() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        final String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param strFormat yyyyMMddhhmmss
     * @return 当前时间的格式
     */
    public static String getUserDate(final String strFormat) {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
        return formatter.format(currentTime);
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHour(final String st1, final String st2) {
        final String[] kk;
        final String[] jj;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0])) {
            return "0";
        } else {
            final double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            final double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0) {
                return y - u + "";
            } else {
                return "0";
            }
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(final String sj1, final String sj2) {
        final SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        final long day;
        try {
            final Date date = myFormatter.parse(sj1);
            final Date myDate = myFormatter.parse(sj2);
            day = (date.getTime() - myDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (final Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(final String sj1, final String jj) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            final Date date1 = format.parse(sj1);
            final long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            return format.format(date1);
        } catch (final Exception e) {
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowDate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(final String nowDate, final String delay) {
        try {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            final Date d = strToDate(nowDate);
            final long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            return format.format(d);
        } catch (final Exception e) {
            return "";
        }
    }

    /**
     * 判断是否润年
     *
     * @param date date
     * @return {boolean}
     */
    public static boolean isLeapYear(final String date) {

        /*
        1.被400整除是闰年，否则：
        2.不能被4整除则不是闰年
        3.能被4整除同时不能被100整除则是闰年
        3.能被4整除同时能被100整除则不是闰年
         */
        final Date d = strToDate(date);
        final GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        final int year = gc.get(Calendar.YEAR);
        return (year % 400) == 0 || (year % 4) == 0 && (year % 100) != 0;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     *
     * @param str s
     * @return string
     */
    public static String getEDate(final String str) {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final ParsePosition pos = new ParsePosition(0);
        final Date str2Date = formatter.parse(str, pos);
        final String j = str2Date.toString();
        final String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * @param date date
     * @return 一个月的最后一天
     */
    public static String getEndDateOfMonth(final String date) {// yyyy-MM-dd
        String str = date.substring(0, 8);
        final String month = date.substring(5, 7);
        final int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(date)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1 date1
     * @param date2 date2
     * @return boolean
     */
    public static boolean isSameWeekDates(final Date date1, final Date date2) {
        final Calendar cal1 = Calendar.getInstance();
        final Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        final int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 当前时间所在的年度是第几周
     */
    public static String getSeqWeek() {
        final Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1) {
            week = "0" + week;
        }
        final String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * @param date date
     * @param num  num
     * @return 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     */
    public static String getWeek(final String date, final String num) {
        // 再转换为时间
        final Date dd = DateUtil.strToDate(date);
        final Calendar c = Calendar.getInstance();
        c.setTime(dd);
        switch (num) {
            case "1": // 返回星期一所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;
            case "2": // 返回星期二所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                break;
            case "3": // 返回星期三所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                break;
            case "4": // 返回星期四所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                break;
            case "5": // 返回星期五所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                break;
            case "6": // 返回星期六所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            case "0": // 返回星期日所在的日期
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * @param date date
     * @return 星期几的字符串
     */
    public static String getWeek(final String date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.strToDate(date));
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static String getWeekStr(final String strDate) {
        String str;
        str = DateUtil.getWeek(strDate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * @param date1 date1
     * @param date2 date2
     * @return 两个时间之间的天数
     */
    public static long getDays(final String date1, final String date2) {
        if (date1 == null || date1.equals("")) {
            return 0;
        }
        if (date2 == null || date2.equals("")) {
            return 0;
        }
        // 转换为标准时间
        final SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        final Date date;
        final Date myDate;
        try {
            date = myFormatter.parse(date1);
            myDate = myFormatter.parse(date2);
            return (date.getTime() - myDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return Long.MIN_VALUE;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     *
     * @param strDate strDate
     * @return 该日历第一行星期日所在的日期
     */
    public static String getNowMonth(final String strDate) {
        // 得到这个月的1号是星期几
        final Date date = DateUtil.strToDate(strDate.substring(0, 8) + "01");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        final int u = c.get(Calendar.DAY_OF_WEEK);
        return DateUtil.getNextDay(strDate, (1 - u) + "");
    }

    public static boolean validateDate(final String date) {
        final SimpleDateFormat sdf;
        if (date == null) {
            return false;
        }
        if (date.length() > 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            sdf.parse(date);
            return true;
        } catch (final ParseException pe) {
            return false;
        }
    }

    /**
     * 2个日期相差的天数
     *
     * @param date2 开始日期（第一个时间）(例如2月1号到2月28 是28天)
     * @param date1 截止日期（第二个时间）
     * @return int 计算后相差的天数
     */
    public static int dateDiff(Date date1, Date date2) {
        int result = 0;
        DateUtil et = new DateUtil();
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();
        gc1.setTime(date1);
        gc2.setTime(date2);
        result = et.getDays(gc1, gc2);
        return result+1;
    }

    /**
     * 2个日期相差的天数
     *
     * @param startData 开始日期（第一个时间）
     * @param endData   截止日期（第二个时间）
     * @return int 计算后相差的天数
     */
    public static int dateDiff(String startData, String endData) {
        return dateDiff(DateUtil.toDate(startData), DateUtil.toDate(endData));
    }
    /**
    　　* @Description: 两个数字日期之间的差值天数
    　　* @param tags　　
       ∗ @return{return_type}
    　　* @throws
    　　* @author sl.qiu　　
       ∗  @date 2019/3/29 9:44
    */
    public static int dateDiffByNumber(String startData, String endData) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(1900, 0, -1);
        Date d = calendar.getTime();
        Date ddStart = DateUtils.addDays(d, Integer.valueOf(startData));
        Date ddEnd = DateUtils.addDays(d, Integer.valueOf(endData));
        int i=dateDiff(ddStart,ddEnd);
        return i;
    }

    /**
     * 2个日期相差的天数
     *
     * @param startData 开始日期（第一个时间）
     * @param endData   截止日期（第二个时间）
     * @return int 计算后相差的天数
     */
    public static int dateDiff(String startData, Date endData) {
        return dateDiff(DateUtil.toDate(startData), endData);
    }

    /**
     * 2个日期相差的天数
     *
     * @param startData 开始日期（第一个时间）
     * @param endData   截止日期（第二个时间）
     * @return int 计算后相差的天数
     */
    public static int dateDiff(Date startData, String endData) {
        return dateDiff(startData, DateUtil.toDate(endData));
    }

    /**
     * @param @param date 日期
     * @param @param day 要加的天数
     * @return Date 返回计算后的新时间
     * @Title: dateAdd
     * @Description: TODO 日期加上天数的计算
     */
    public static Date dateAdd(Date date, int day) {
        DateUtil dateUtil = new DateUtil();
        GregorianCalendar thisday = dateUtil.getThisDate(date, day,
                GregorianCalendar.DATE);
        return new Date(thisday.getTime().getTime());
    }

    /**
     * @param @param date 日期
     * @param @param day 要加的天数
     * @return Date 返回计算后的新时间
     * @Title: dateAdd
     * @Description: TODO 日期加上天数的计算
     */
    public static Date dateAdd(String date, int day) {
        return dateAdd(DateUtil.toDate(date), day);
    }

    /**
     * @param date 日期
     * @param hour  要加的小时数
     * @return Date 返回计算后的新时间
     * @Title: hourAdd
     * @Description: TODO 日期加上小时的计算
     */
    public static Date hourAdd(Date date, int hour) {
        DateUtil dateUtil = new DateUtil();
        GregorianCalendar thisday = dateUtil.getThisDate(date, hour,
                GregorianCalendar.HOUR);
        return new Date(thisday.getTime().getTime());
    }

    /**
     * @param date 日期
     * @param hour  要加的小时数
     * @return Date 返回计算后的新时间
     * @Title: hourAdd
     * @Description: TODO 日期加上小时的计算
     */
    public static Date hourAdd(String date, int hour) {
        return hourAdd(DateUtil.toDate(date), hour);
    }

    /**
     * @param date 日期
     * @param month  要加的月数
     * @return Date 返回计算后的新时间
     * @Title: monthAdd
     * @Description: TODO 日期根据月的计算
     */
    public static Date monthAdd(Date date, int month) {
        DateUtil dateUtil = new DateUtil();
        GregorianCalendar thisday = dateUtil.getThisDate(date, month,
                GregorianCalendar.MONTH);
        return new Date(thisday.getTime().getTime());
    }

    /**
     * @param date 日期
     * @param month  要加的月数
     * @return Date 返回计算后的新时间
     * @Title: monthAdd
     * @Description: TODO 日期根据月的计算
     */
    public static Date monthAdd(String date, int month) {
        return monthAdd(DateUtil.toDate(date), month);
    }

    /**
     * @param date 日期
     * @param minute  要加的分钟数
     * @return Date 返回计算后的新时间
     * @Title: minuteAdd
     * @Description: TODO 日期根据分钟的计算
     */
    public static Date minuteAdd(Date date, int minute) {
        DateUtil dateUtil = new DateUtil();
        GregorianCalendar thisday = dateUtil.getThisDate(date, minute,
                GregorianCalendar.MINUTE);
        return new Date(thisday.getTime().getTime());
    }

    /**
     * @param date 日期
     * @param minute  要加的分钟数
     * @return Date 返回计算后的新时间
     * @Title: minuteAdd
     * @Description: TODO 日期根据分钟的计算
     */
    public static Date minuteAdd(String date, int minute) {
        return minuteAdd(DateUtil.toDate(date), minute);
    }

    /**
     * 时间转换 2006/12/12 12:31:20 to 2006-12-12
     *
     * @param d
     * @return
     */

    public static String toDateString2(String d) {
        if (d != null && d.length() > 0) {
            String date = d.substring(0, d.lastIndexOf(" "));
            String date2 = d.substring(d.lastIndexOf(" ") + 1, d.length());
            String[] array = date.split("/");
            String newdate = array[2] + "-" + array[0] + "-" + array[1] + " "
                    + date2;
            return newdate;
        } else
            return null;
    }

    /**
     * 时间转换 061212 to 2006-12-12
     *
     * @param d
     * @return
     */
    public static String toDateString(String d) {
        // 原来时间格式为061226,转换为
        if (d != null && d.trim().length() == 6) {
            String year = d.substring(0, 2);
            String mouth = d.substring(2, 4);
            String day = d.substring(4, 6);
            String newdate = "20" + year + "-" + mouth + "-" + day;
            return newdate;
        } else
            return "";
    }

    /**
     * 时间转换 2006-12-12 to 20061212000000
     *
     * @param d
     * @return
     */
    public static String toDateString6(String d) {
        if (!StringUtils.isEmpty(d)) {
            return d.replaceAll("-", "") + "000000";
        } else
            return "";
    }

    /**
     * 时间转换 2006-12-12 to 061212
     *
     * @param d
     * @return
     */
    public static String toDateString3(String d) {
        if (!StringUtils.isEmpty(d)) {
            return d.replaceAll("-", "").substring(2);
        } else
            return "";
    }

    /**
     * 时间转换 2006-12-12 12:12:12 to 20061212121212
     * 14位
     *
     * @param d
     * @return
     */
    public static String toDateString14(String d) {
        if (!StringUtils.isEmpty(d)) {
            return d.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
        } else
            return "";
    }

    /**
     * 时间转换 061212 to 5月5号
     *
     * @param d
     * @return
     */
    public static String toDateString5(String d) {
        // 原来时间格式为061226,转换为
        if (!StringUtils.isEmpty(d)) {
            String year = d.substring(0, 2);
            String mouth = d.substring(2, 4);
            String day = d.substring(4, 6);
            String newdate = new Integer(mouth) + "月" + new Integer(day) + "日";
            return newdate;
        } else
            return "";
    }

    public static String toDateString4(String d) {
        if (d != null && d.length() > 10) {
            return d.substring(0, 10);
        } else {
            return "";
        }
    }

    /**
     * 将日期型转换成短显示格式，格式为"YYYY-MM-DD hh:mm:ss"
     *
     * @param d
     * @return
     */
    public static String dispStringLong(Date d) {
        return DateUtil.format(d, "yyyy'-'MM'-'dd' 'HH':'mm':'ss");
    }

    /**
     * 将日期型转换成短显示格式，格式为"DD-Mon-YY"
     *
     * @param d
     * @return
     */
    public static String dispStringEn(Date d) {
        if (null == d) {
            return "";
        }
        String date = DateUtil.format(d, "dd'-'MM'-'yy");
        Map<String, String> map = new HashMap<String, String>();
        map.put("01", "Jan");
        map.put("02", "Feb");
        map.put("03", "Mar");
        map.put("04", "Apr");
        map.put("05", "May");
        map.put("06", "Jun");
        map.put("07", "Jul");
        map.put("08", "Aug");
        map.put("09", "Sep");
        map.put("10", "Otc");
        map.put("11", "Nov");
        map.put("12", "Dec");
        String[] strs = date.split("-");

        strs[1] = map.get(strs[1]);
        return strs[0] + "-" + strs[1] + "-" + strs[2];
    }

    /**
     * 将日期型转换成短显示格式，格式为"YYYY-MM-DD"
     *
     * @param d
     * @return
     */
    public static String dispShort(Date d) {
        return DateUtil.format(d, "yyyy'-'MM'-'dd");
    }

    /**
     * 将日期型转换成短显示格式，格式为"yyMMdd"
     *
     * @param d
     * @return
     */
    public static String dispShort3(Date d) {
        return DateUtil.format(d, "yyMMdd");
    }

    /**
     * 将日期型转换成短显示格式，格式为"MMddyy"
     *
     * @param d
     * @return
     */
    public static String dispShort4(Date d) {
        return DateUtil.format(d, "MM'/'dd'/'yy");
    }

    /**
     * 将日期型转换成短显示格式，格式为"YYYYMMDD"
     *
     * @param d
     * @return
     */
    public static String dispShort2(Date d) {
        return DateUtil.format(d, "yyyyMMdd");
    }

    /**
     * 将日期型转换成短中文显示格式，格式为"YYYY年MM月DD日"
     *
     * @param d
     * @return
     */
    public static String dispShortCn(Date d) {
        return DateUtil.format(d, "yyyy'年'M'月'd'日'");
    }

    /**
     * 将日期型转换成短中文显示格式，格式为"YYYY年MM月DD日下午K点"
     *
     * @param d
     * @return
     */
    public static String dispMedCn(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'M'月'd'日'aK'点'",
                Locale.SIMPLIFIED_CHINESE);
        return sdf.format(d);
    }

    public static String dispUTC(Date d) {

        return DateUtil.format(d, "yyyy'-'MM'-'dd'T'HH':'mm':'ss+08:00");

    }

    /**
     * 将日期型转换成长显示格式，格式为"YYYY-MM-DD HH:MI:SS"
     *
     * @param d
     * @return
     */
    public static String dispLong(Date d) {
        return DateUtil.format(d, "yyyy'-'MM'-'dd' 'HH':'mm':'ss");
    }

    /**
     * 将日期型转换成长显示格式，格式为"YYYY-MM-DD HH:MI"
     *
     * @param d
     * @return
     */
    public static String dispLong2(Date d) {
        return DateUtil.format(d, "yyyy'-'MM'-'dd' 'HH':'mm");
    }

    /**
     * 将日期型转换成12位长字符串，格式为"YYYYMMDDHHMISS"
     *
     * @param d
     * @return
     */
    public static String dispLong3(Date d) {
        return DateUtil.format(d, "yyyyMMddHHmmss");
    }

    /**
     * 将日期型转换成12位长字符串，格式为"YYYYMMDDHHMI"
     *
     * @param d
     * @return
     */
    public static String dispLong4(Date d) {
        return DateUtil.format(d, "yyyyMMddHHmm");
    }

    public static String dispLong5(Date d) {
        return DateUtil.format(d, "yyMMddHHmmss");
    }

    /**
     * 将日期型转换成长中文显示格式，格式为"YYYY年MM月DD日 HH时MI分"
     *
     * @param d
     * @return
     */

    public static String dispLongCn(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy'年'MM'月'dd'日'HH'时'mm'分'");
        return sdf.format(d);
    }

    /**
     * @param @param date 日期
     * @param @param format 格式
     * @return String 返回新的日期
     * @throws
     * @Title: format
     * @Description: TODO 将日期转换为想要的格式
     */
    public static String format(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return format(DateUtil.toDate(date), format);
    }

    /**
     * @param @param date 日期
     * @param @param format 格式
     * @return String 返回新的日期
     * @throws
     * @Title: format
     * @Description: TODO 将日期转换为想要的格式
     */
    public static String format(Date date, String format) {
        if (null == date) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * @param ss
     * @return
     * @throws ParseException
     * @desc 处理日期的年月日格式. e.g: 1997-01-02 处理后为 19970102
     */
    private static String toFont(String ss) {

        String year, month, day;
        int j = ss.indexOf("-");
        if (j == 4) {
            year = ss.substring(0, 4);
            int k = ss.substring(5).indexOf("-");
            if (k == 1) {
                month = "0" + ss.substring(5).substring(0, 1);
                if (ss.substring(5).substring(2).length() == 1) {
                    day = "0" + ss.substring(5).substring(2);
                } else if (ss.substring(5).substring(2).length() == 2) {
                    day = ss.substring(5).substring(2);
                } else {
                    return ss;
                }
            } else if (k == 2) {
                month = ss.substring(5).substring(0, 2);
                if (ss.substring(5).substring(3).length() == 1) {
                    day = "0" + ss.substring(5).substring(3);
                } else if (ss.substring(5).substring(3).length() == 2) {
                    day = ss.substring(5).substring(3);
                } else {
                    return ss;
                }
            } else { // 如果月份长度大于二
                return ss;
            }
        } else { // 如果年份长度不等于四
            return ss;
        }
        return (year + month + day);

    }

    /**
     * @param ss
     * @return
     * @throws ParseException
     * @desc 处理时间格式. e.g: 22:20:00 处理后为222000
     */
    private static String toEnd(String ss) {

        String hour, min, sec;
        int j = ss.indexOf(":");
        if (j == 2) {
            hour = ss.substring(0, 2);
            int k = ss.substring(3).indexOf(":");
            if (k == 1) {
                min = "0" + ss.substring(3).substring(0, 1);
                if (ss.substring(3).substring(2).length() == 1) {
                    sec = "0" + ss.substring(3).substring(2);
                } else if (ss.substring(3).substring(2).length() == 2) {
                    sec = ss.substring(3).substring(2);
                } else {
                    return ss;
                }
            } else if (k == 2) {
                min = ss.substring(3).substring(0, 2);
                if (ss.substring(3).substring(3).length() == 1) {
                    sec = "0" + ss.substring(3).substring(3);
                } else if (ss.substring(3).substring(3).length() == 2) {
                    sec = ss.substring(3).substring(3);
                } else {
                    return ss;
                }
            } else { // 如果分钟长度大于二
                return ss;
            }
        } else if (j == 1) {
            hour = "0" + ss.substring(0, 1);
            int k = ss.substring(2).indexOf(":");
            if (k == 1) {
                min = "0" + ss.substring(2).substring(0, 1);
                if (ss.substring(2).substring(2).length() == 1) {
                    sec = "0" + ss.substring(2).substring(2);
                } else if (ss.substring(2).substring(2).length() == 2) {
                    sec = ss.substring(2).substring(2);
                } else {
                    return ss;
                }
            } else if (k == 2) {
                min = ss.substring(2).substring(0, 2);
                if (ss.substring(2).substring(3).length() == 1) {
                    sec = "0" + ss.substring(2).substring(3);
                } else if (ss.substring(2).substring(3).length() == 2) {
                    sec = ss.substring(2).substring(3);
                } else {
                    return ss;
                }
            } else { // 如果分钟长度大于二
                return ss;
            }

        } else { // 如果小时长度大于二
            return ss;
        }
        return (hour + min + sec);

    }

    /**
     * <b>功能说明：</b><br>
     * 将生日字段转化为年龄
     *
     * @param birthStr 生日字段 "yyyymmdd"
     * @return 年龄
     */

    public static String birthDateToAge(String birthStr) {
        if (birthStr == null || birthStr.length() < 8) {
            return "0";
        } else {
            try {
                int birthYear = (Integer.valueOf(birthStr.substring(0, 4)))
                        .intValue();
                GregorianCalendar gc = new GregorianCalendar();
                int age = gc.get(Calendar.YEAR) - birthYear + 1;
                return "" + age;
            } catch (NumberFormatException ex) {
                return birthStr;
            }
        }
    }

    /**
     * <b>功能说明：</b><br>
     * 将生日字段转化为年龄
     *
     * @param birthStr 生日字段 "yyyymmdd"
     * @return 年龄
     */

    public static short birthDateToAge2(String birthStr) {
        if (birthStr == null || birthStr.length() < 8) {
            return (short) 0;
        } else {
            try {
                int birthYear = (Integer.valueOf(birthStr.substring(0, 4)))
                        .intValue();
                GregorianCalendar gc = new GregorianCalendar();
                short age = (short) (gc.get(Calendar.YEAR) - birthYear + 1);
                return age;
            } catch (NumberFormatException ex) {
                return (short) 0;
            }
        }
    }

    /**
     * 将字符串转换成日期格式
     * 尝试进行不合法的字符串转换
     *
     * @return
     * @throws
     */
    public static Date toDate(String date) {
        if (StringUtils.isEmpty(date)) {
            throw new RuntimeException(
                    "Illegal Data String: must be 8 or 12 chars");
        }
        Date dateTemp;
        try {
            switch (date.length()) {
                case 19:
                    dateTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .parse(date);
                    break;
                case 14:
                    dateTemp = new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
                    break;
                case 12:
                    dateTemp = new SimpleDateFormat("yyyyMMddHHmm").parse(date);
                    break;
                case 16:
                    dateTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
                    break;
                case 10:
                    dateTemp = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    break;
                case 8:
                    dateTemp = new SimpleDateFormat("yyyyMMdd").parse(date);
                    break;
                case 6:
                    dateTemp = new SimpleDateFormat("yyMMdd").parse(date);
                    break;
                default:
                    throw new RuntimeException(
                            "Illegal Data String: must be 8 or 12 chars");
            }
        } catch (Exception e) {
            throw new RuntimeException(
                    "Illegal Data String: must be 8 or 12 chars");
        }
        return dateTemp;
    }

    public static String getCurrentDate() {
        return DateUtil.dispShort(new Date());
    }

    public static String getCurrentTime() {
        return DateUtil.dispLong(new Date());
    }

    public static String getCurrentYearMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(new Date());
    }

    public static String getYearMonthList(String yearMonth) {
        StringBuffer sb = new StringBuffer();
        int month = Integer.parseInt(yearMonth.substring(4));
        String year = yearMonth.substring(0, 4);
        for (int i = 1; i <= 12; i++) {
            sb.append("\n<option value=" + year + (i < 10 ? "0" + i : "" + i));
            if (i == month) {
                sb.append("  selected");
            }
            sb.append(">" + year + "-" + (i < 10 ? "0" + i : "" + i)
                    + "</option>");
        }
        return sb.toString();
    }

    /**
     * @param yearMonth
     * @return
     * @desc 返回两年的月份列表
     */
    public static String getTwoYearMonthList(String yearMonth) {
        StringBuffer sb = new StringBuffer();
        int month = Integer.parseInt(yearMonth.substring(4));
        String year = yearMonth.substring(0, 4);
        String lastyear = String.valueOf(Integer.parseInt(year) - 1);
        for (int i = 1; i <= 12; i++) {
            sb.append("\n<option value=" + lastyear
                    + (i < 10 ? "0" + i : "" + i));
            sb.append(">" + lastyear + "-" + (i < 10 ? "0" + i : "" + i)
                    + "</option>");
        }

        for (int i = 1; i <= 12; i++) {
            sb.append("\n<option value=" + year + (i < 10 ? "0" + i : "" + i));
            if (i == month) {
                sb.append("  selected");
            }
            sb.append(">" + year + "-" + (i < 10 ? "0" + i : "" + i)
                    + "</option>");
        }
        return sb.toString();
    }

    /**
     * <b>功能说明：</b><br>
     * 把长日期转换成存储格式,例如把2004年3月12日下午2点，转换成20040312140000 目前不支持刻/半 <br>
     *
     * @param s String
     * @return String
     * @throws
     * @author 蔡
     */
    public static String longToStore(String s) {
        // 最后结果
        StringBuffer after = new StringBuffer();
        // 日期分割
        final String BREAK[] = new String[]{"年", "月", "日", "点", "分", "秒",
                "年", "月", "日", "时", "分", "秒", "/", "/", " ", ":", ":", null,
                "/", "/", ",", ":", ":", null, "-", "-", " ", ":", ":", null,
                "-", "-", ",", ":", ":", null};
        // 如果不是数字的，对应要加数值
        final String[] ADD = new String[]{"上午", "下午", "半"};
        // 数值
        final int[] ADD_ = {0, 12, 30};
        // 起始位置
        int start = 0;
        for (int i = 0; i < 6; i++) {
            // 找到的分隔符的位置
            int p = -1;
            int j = 0;
            while (p < 0 && j < 6) {
                if (BREAK[i + j * 6] != null) {
                    p = s.indexOf(BREAK[i + j * 6], start);
                    j++;
                } else {
                    p = s.length();
                }
            }
            // 如果循环了6次都没找到，那就是没有这个分隔符，继续取下一个
            if (j >= 6) {
                // 如果剩余长度小于等于2，就是快结束了.
                if (s.length() - start <= 2) {
                    p = s.length();
                } else {
                    after.append("00");
                    continue;
                }
            }
            String xTime = null;
            xTime = s.substring(start, p);
            String x = "";
            // 试着把这段的字符串转换成整形，如果转换不成功可能就是包含中文的日期，再试着转换
            try {
                x = String.valueOf(Integer.parseInt(xTime));
                after.append((x.length() > 1) ? x : "0" + x);
            } catch (NumberFormatException ex) {
                for (j = 0; j < ADD.length; j++) {
                    if (xTime.indexOf(ADD[j]) >= 0) {
                        break;
                    }
                }
                if (j < ADD.length) {
                    StringTokenizer st1 = new StringTokenizer(
                            xTime, ADD[j]);
                    String tt;
                    try {
                        if (st1.hasMoreElements()) {
                            tt = String.valueOf(ADD_[j]
                                    + Integer.parseInt(st1.nextElement()
                                    .toString()));
                        } else {
                            tt = String.valueOf(ADD_[j]);
                        }
                    } catch (NumberFormatException ex1) {
                        tt = String.valueOf(ADD_[j]);
                    }
                    after.append(tt.length() >= 2 ? tt : "0" + tt);
                } else {
                    after.append("00");
                }
            }
            start = p + BREAK[i].length();
            if (start > s.length()) {
                start = s.length(); // /break;
            }
        }
        return after.toString();
    }

    /**
     * 4/12/2007 12:00:00 AM
     * 将4/12/2007 12:00:00 AM转换成日期格式
     */
    public static Date getDate(String date) throws ParseException {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        String[] info = date.split(" ");

        String[] dates = info[0].split("/");
        if (dates[0].length() < 2) {
            dates[0] = "0" + dates[0];
        }
        if (dates[1].length() < 2) {
            dates[1] = "0" + dates[1];
        }

        info[0] = dates[2] + "-" + dates[0] + "-" + dates[1];
        String[] time = info[1].split(":");

        if (time[0].length() < 2) {
            time[0] = "0" + time[0];
        }
        if (time[1].length() < 2) {
            time[1] = "0" + time[1];
        }
        if (time[2].length() < 2) {
            time[2] = "0" + time[2];
        }

        if ("PM".equals(info[2]) && !"12".equals(time[0])) {
            info[1] = new Integer(new Integer(time[0]).intValue() + 12)
                    .toString()
                    + ":" + time[1] + ":" + time[2];
        } else if ("AM".equals(info[2]) && "12".equals(time[0])) {
            info[1] = "00:" + time[1] + ":" + time[2];
        } else {
            info[1] = time[0] + ":" + time[1] + ":" + time[2];
        }
        return toDate(info[0] + " " + info[1]);

    }

    /**
     * @param @param  date
     * @param @return 设定文件
     * @return Date 返回类型
     * @throws
     * @Title: getDateShort
     * @Description: TODO 将2009-1-1或2009-1-1 12:30装换为2009-01-01
     */
    public static String getDateShort(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        String[] info = date.trim().split(" ");
        if (StringUtils.isEmpty(info[0])) {
            return null;
        }
        String[] dates = info[0].split("-");
        if (dates[1].length() < 2) {
            dates[0] = "0" + dates[0];
        }
        if (dates[2].length() < 2) {
            dates[1] = "0" + dates[1];
        }
        info[0] = dates[0] + "-" + dates[1] + "-" + dates[2];
        return info[0];
    }

    /**
     * @param @param  date
     * @param @return 设定文件
     * @return Date 返回类型
     * @throws
     * @Title: getDateShort
     * @Description: TODO 将2009-1-1或2009-1-1 12:30装换为2009-01-01 00:00:00
     */
    public static String getDateLong(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }

        String[] info = date.trim().split(" ");
        if (null == info || info.length <= 0) {
            return null;
        }
        if (StringUtils.isBlank(info[0])) {
            return null;
        }
        String[] dates = info[0].split("/");
        if (dates.length <= 2) {
            dates = info[0].split("-");
        }
        if (dates[1].length() < 2) {
            dates[1] = "0" + dates[1];
        }
        if (dates[2].length() < 2) {
            dates[2] = "0" + dates[2];
        }

        String timeStr = "00:00:00";
        if (info.length > 1 && null != info[1]) {


            info[0] = dates[0] + "-" + dates[1] + "-" + dates[2];
            String[] times = info[1].split(":");
            StringBuilder timeSb = new StringBuilder();
            for (int i = 0; i < times.length; i++) {
                if (times[i].length() < 2) {
                    times[i] = "0" + times[i];
                }
                timeSb.append(":").append(times[i]);
            }
            timeStr = timeSb.toString();
            if (timeStr.length() > 0) {
                timeStr = timeStr.substring(1);
            }
            if (timeStr.length() < 8) {
                int k = 8 - timeStr.length();
                String tempZero = "";
                for (int i = 0; i < k; i++) {
                    tempZero += "0";
                }
                timeStr = timeStr + ":" + tempZero.substring(1);
            } else if (timeStr.length() > 8) {
                timeStr = timeStr.substring(0, 8);
            }

        }
        return info[0] + " " + timeStr;
    }

    public static javax.xml.datatype.XMLGregorianCalendar getXMLGregorianCalendar(
            Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory
                    .newInstance();
            return dtf.newXMLGregorianCalendar(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar
                            .get(Calendar.DAY_OF_MONTH), calendar
                            .get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND), calendar
                            .get(Calendar.MILLISECOND), calendar
                            .get(Calendar.ZONE_OFFSET)
                            / (1000 * 60));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Calendar setStartDay(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar;
    }

    public static Calendar setEndDay(Calendar calendar) {
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar;
    }

    /**
     * 本周第一天
     *
     * @return
     */
    public static Date getWeekFirstDay(int diff) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.dateAdd(new Date(), diff * 7));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 本周最后一天
     *
     * @return
     */
    public static Date getWeekLastDay(int diff) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getWeekFirstDay(diff));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 本月第一天
     *
     * @return
     */
    public static Date getMonthFirstDay(int diff) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.monthAdd(new Date(), diff));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
//    计算指定月份的开始日期，
    public static String getDefaultMonthFirstDay(String defaultDate) {
        defaultDate=defaultDate.substring(0,7);
        return defaultDate+"-01";
    }
    //    计算指定月份的结束日期
    public static String getDefaultMonthEndDay(String defaultDate) {
        defaultDate=defaultDate.substring(0,7);
        String[] split = defaultDate.split("-");
        String month=split[1];
        String maxMonth="01,03,05,07,09,11";
        String minMonth="04,06,08,10,12";
        if(maxMonth.indexOf(month)>=0){
            defaultDate=defaultDate+"-31";
        }else if(minMonth.indexOf(month)>=0){
            defaultDate=defaultDate+"-30";
        }else{
            defaultDate=defaultDate+"-28";
        }
        return defaultDate;
    }
    /**
     * 本月最后一条
     *
     * @return
     */
    public static Date getMonthLastDay(int diff) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.monthAdd(new Date(), diff));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    /**
     * 本季度第一天
     * diff   0 代表本季度   -1代表上一季度  1 代表下一季度
     *
     * @return
     */
    public static Date getQuarterFirstDay(int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.monthAdd(new Date(), diff * 3));

        int currentMonth = c.get(Calendar.MONTH);
        currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }

            c.set(Calendar.DATE, 1);

            now = DateUtil.toDate(DateUtil.dispShort2(c.getTime()) + "000000");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 本季度最后一天
     *
     * @return
     */
    public static Date getQuarterLastDay(int diff) {

        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.monthAdd(new Date(), diff * 3));
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = DateUtil.toDate(DateUtil.dispShort2(c.getTime()) + "235959");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public static String formatEnDate(Date date) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return simpledateformat.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
    }

    /**
     * yyyy/mm/dd date转成yyyy-mm-dd
     */
    public static String dateToNewDateLong(String inDate) {
        SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
        String outDate = "";
        try {
            outDate = out.format(in.parse(inDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return outDate;
        }
        return outDate;
    }

    /**
     * yyyy/mm/dd date转成yyyy-mm-dd
     */
    public static String dateToNewDateShort(String inDate) {
        SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM");
        String outDate = "";
        try {
            outDate = out.format(in.parse(inDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return inDate;
        }
        return outDate;
    }

    public static void main(String[] args) throws Exception {
    }

    protected int getDays(GregorianCalendar g1, GregorianCalendar g2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        if (g2.after(g1)) {
            return elapsed;
        } else {
            return -elapsed;
        }

    }

    protected GregorianCalendar getThisDate(Date date, int i,
                                            int gregorianCalendarType) {
        GregorianCalendar thisday = new GregorianCalendar();
        thisday.setTime(date);
        thisday.add(gregorianCalendarType, i);
        return thisday;
    }

    //传入的数据为毫秒数
    public static String formattime(long time) {
        String min = (time / (1000 )) + "";
        String second = (time % (1000) ) + "";
//        if (min.length() < 2) {
//            min = 0 + min;
//        }
//        if (second.length() < 2) {
//            second = 0 + second;
//        }
        return min + "." + second;
    }

}