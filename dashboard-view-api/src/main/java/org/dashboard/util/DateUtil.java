package org.dashboard.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * @ClassName: DateUtil
 * @Auther: jchan
 * @Date: 2019/7/1 上午11:08
 * @Version 1.0 2019/7/1 上午11:08 by 韩进城(hanjin7278@126.com)
 * @Description:
 **/
public class DateUtil {
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";
    public static final String FORMAT_FOUR = "yyyyMMddHHmmss";
    public static final String FORMAT_FIVE = "MM-dd HH:mm";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";
    public static final String EIGHT_STYLE_DATE_FORMAT = "yyyyMMdd";
    public static final String SHORT_DATE_FORMAT = "MM-dd";
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";
    public static final String MONTH_DATE_FORMAT_2 = "yyyy/MM";
    public static final int SUB_YEAR = 1;
    public static final int SUB_MONTH = 2;
    public static final int SUB_DAY = 5;
    public static final int SUB_HOUR = 10;
    public static final int SUB_MINUTE = 12;
    public static final int SUB_SECOND = 13;
    public static final String FORMAT_Five_New = "yyyyMMddHHmmssSS";
    static final String[] dayNames = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateUtil() {
    }

    public static Date toDate(String dateStr) {
        Date d = null;
        if (dateStr != null && dateStr.length() == 10) {
            dateStr = MessageFormat.format("{0} 00:00:00", dateStr);
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception var4) {
            d = null;
        }

        return d;
    }

    public static Date toDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);

        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception var5) {
            d = null;
        }

        return d;
    }

    public static String formatDateTime(Date date) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            result = formater.format(date);
        } catch (Exception var4) {
            ;
        }

        return result;
    }

    public static String formatDateTime(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);

        try {
            result = formater.format(date);
        } catch (Exception var5) {
            ;
        }

        return result;
    }

    public static String getCurrDate(String format) {
        return formatDateTime(new Date(), format);
    }

    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = toDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return formatDateTime(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static long timeSub(String firstTime, String secTime) {
        long first = toDate(firstTime, "yyyy-MM-dd HH:mm:ss").getTime();
        long second = toDate(secTime, "yyyy-MM-dd HH:mm:ss").getTime();
        return (second - first) / 1000L;
    }

    public static long timeSub(Date firstTime, Date secTime) {
        long first = firstTime.getTime();
        long second = secTime.getTime();
        return (second - first) / 1000L;
    }

    public static int getDaysOfMonth(String year, String month) {
        byte days;
        if (!month.equals("1") && !month.equals("3") && !month.equals("5") && !month.equals("7") && !month.equals("8") && !month.equals("10") && !month.equals("12")) {
            if (!month.equals("4") && !month.equals("6") && !month.equals("9") && !month.equals("11")) {
                if ((Integer.parseInt(year) % 4 != 0 || Integer.parseInt(year) % 100 == 0) && Integer.parseInt(year) % 400 != 0) {
                    days = 28;
                } else {
                    days = 29;
                }
            } else {
                days = 30;
            }
        } else {
            days = 31;
        }

        return days;
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(5);
    }

    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(5);
    }

    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(2) + 1;
    }

    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1);
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) + 1;
    }

    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000L;
    }

    public static int yearDiff(String before, String after) {
        Date beforeDay = toDate(before, "yyyy-MM-dd");
        Date afterDay = toDate(after, "yyyy-MM-dd");
        return getYear(afterDay) - getYear(beforeDay);
    }

    public static int monthDiff(Date before, Date after) {
        return getMonth(after) - getMonth(before);
    }

    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = toDate(after, "yyyy-MM-dd");
        return getYear(beforeDay) - getYear(afterDay);
    }

    public static long dayDiffCurr(String before) {
        Date currDate = toDate(currDay(), "yyyy-MM-dd");
        Date beforeDate = toDate(before, "yyyy-MM-dd");
        return (currDate.getTime() - beforeDate.getTime()) / 86400000L;
    }

    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(7);
        c.set(year, month - 1, 1);
        return c.get(7);
    }

    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(7);
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(7);
    }

    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return formatDateTime(today.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }

        if (!isDate(birth)) {
            return "";
        } else {
            int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1, birth.lastIndexOf("-")));
            int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
            String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
            int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
            int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
            return s.substring(start, start + 2) + "座";
        }
    }

    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    public static boolean isDateTime(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))[//-]?");
        reg.append("((((0?[13578])|(1[02]))[//-]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])");
        reg.append("|(11))[//-]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-]?((0?[1-9])|([1-2][0-9])))))|");
        reg.append("(\\d{2}(([02468][1235679])|([13579][01345789]))[//-]?((((0?[13578])|(1[02]))[//-]?");
        reg.append("((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-]?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2[//-]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:");
        reg.append("([0-5]?[0-9])((//s)|(\\:([0-5]?[0-9])))))?$");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(2, months);
        return cal.getTime();
    }

    public static Date getStartOfQuarter(Date date) {
        String dateString = "";
        int year;
        int month;
        if (null == date) {
            Calendar localTime = Calendar.getInstance();
            year = localTime.get(1);
            month = localTime.get(2) + 1;
        } else {
            year = getYear(date);
            month = getMonth(date);
        }

        if (month >= 1 && month <= 3) {
            dateString = year + "-01-01";
        }

        if (month >= 4 && month <= 6) {
            dateString = year + "-04-01";
        }

        if (month >= 7 && month <= 9) {
            dateString = year + "-07-01";
        }

        if (month >= 10 && month <= 12) {
            dateString = year + "-10-01";
        }

        return toDate(dateString);
    }

    public static Date nextYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(1, year);
        return cal.getTime();
    }

    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(6, day);
        return cal.getTime();
    }

    public static Date nextMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(12, minute);
        return cal.getTime();
    }

    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(6, day);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(4, week);
        return cal.getTime();
    }

    public static String currDay() {
        return formatDateTime(new Date(), "yyyy-MM-dd");
    }

    public static String currDays() {
        return formatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String befoDay() {
        return befoDay("yyyy-MM-dd");
    }

    public static String befoDay(String format) {
        return formatDateTime(nextDay(new Date(), -1), format);
    }

    public static String befoMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(2, -1);
        return formatDateTime(cal.getTime(), format);
    }

    public static String afterDay() {
        return formatDateTime(nextDay(new Date(), 1), "yyyy-MM-dd");
    }

    public static int getDayNum() {
        GregorianCalendar gd = new GregorianCalendar();
        Date dt = gd.getTime();
        GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
        Date dt1 = gd1.getTime();
        int daynum = (int)((dt.getTime() - dt1.getTime()) / 86400000L);
        return daynum;
    }

    public static Date getDateByNum(int day) {
        GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
        Date date = gd.getTime();
        date = nextDay(date, day);
        return date;
    }

    public static String getYmdDateCN(String datestr) {
        if (datestr == null) {
            return "";
        } else if (datestr.length() < 10) {
            return "";
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7)).append(datestr.substring(8, 10));
            return buf.toString();
        }
    }

    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        return cal.getTime();
    }

    public static String getFirstSecondOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date getFirstSecondOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getStartOfDay(date));
        cal.set(5, 1);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date getFirstSecondOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getStartOfDay(new Date()));
        cal.set(5, 1);
        cal.set(10, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date getEndSecondOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getEndOfDay(new Date()));
        cal.set(5, cal.getActualMaximum(5));
        return cal.getTime();
    }

    public static Date getEndSecondOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getEndOfDay(date));
        cal.set(5, cal.getActualMaximum(5));
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.add(2, 1);
        cal.add(5, -1);
        return formatDateTime(cal.getTime(), format);
    }

    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.add(2, 1);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static Date getStartOfDay(Date day) {
        return getStartOfDay(day, Calendar.getInstance());
    }

    public static Date getTodayStart() {
        return getStartOfDay(new Date());
    }

    public static Date getBeforeDate(Date mirror, int dayNum) {
        Calendar current = Calendar.getInstance();
        current.setTime(mirror);
        current.add(10, -24 * dayNum);
        return current.getTime();
    }

    public static Date getStartOfDay(Date day, Calendar cal) {
        if (day == null) {
            day = new Date();
        }

        cal.setTime(day);
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        return cal.getTime();
    }

    public static Date getEndOfDay(Date day) {
        return getEndOfDay(day, Calendar.getInstance());
    }

    public static Date getEndOfDay(Date day, Calendar cal) {
        if (day == null) {
            day = new Date();
        }

        cal.setTime(day);
        cal.set(11, cal.getMaximum(11));
        cal.set(12, cal.getMaximum(12));
        cal.set(13, cal.getMaximum(13));
        cal.set(14, cal.getMaximum(14));
        return cal.getTime();
    }

    public static Long timeDiffForDay(Date pBeginTime, Date pEndTime) throws ParseException {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long day = (endL - beginL) / 86400000L;
        return day;
    }

    public static Long timeDiffForHour(Date pBeginTime, Date pEndTime) throws ParseException {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long hour = (endL - beginL) % 86400000L / 3600000L;
        return hour;
    }

    public static Long timeDiffForMin(Date pBeginTime, Date pEndTime) throws ParseException {
        Long beginL = pBeginTime.getTime();
        Long endL = pEndTime.getTime();
        Long min = (endL - beginL) % 86400000L % 3600000L / 60000L;
        return min;
    }

    public static String getTime(Date time) {
        String result = null;
        Long temp = null;
        Date currentDate = new Date();

        try {
            temp = timeDiffForDay(time, currentDate);
            if (temp > 0L) {
                result = temp + "天前";
            } else {
                temp = timeDiffForHour(time, currentDate);
                if (temp > 0L) {
                    result = temp + "小时前";
                } else {
                    temp = timeDiffForMin(time, currentDate);
                    if (temp > 0L) {
                        result = temp + "分种前";
                    }
                }
            }
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return result == null ? "刚才" : result;
    }

    public static Date getMonthDelay(Date currentDay, int month) {
        Calendar current = Calendar.getInstance();
        current.setTime(currentDay);
        current.add(2, month);
        current.add(10, -24);
        return current.getTime();
    }

    public static Date getStartOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        cal.set(5, 1);
        cal.add(2, -1);
        return cal.getTime();
    }

    public static Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        return cal.getTime();
    }

    public static Date getEndOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMaximum(11));
        cal.set(12, cal.getMaximum(12));
        cal.set(13, cal.getMaximum(13));
        cal.set(14, cal.getMaximum(14));
        cal.set(5, 1);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static Date getMinEndOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        cal.set(5, 1);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static int getDaysOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        cal.add(5, -1);
        return cal.get(5);
    }

    public static int getWorkTime(String starttime, String endtime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = null;
        Date dateTo = null;

        try {
            dateFrom = dateFormat.parse(starttime);
            dateTo = dateFormat.parse(endtime);
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

        int workdays = 0;

        for(Calendar cal = null; dateFrom.before(dateTo) || dateFrom.equals(dateTo); dateFrom = cal.getTime()) {
            cal = Calendar.getInstance();
            cal.setTime(dateFrom);
            if (cal.get(7) != 7 && cal.get(7) != 1) {
                ++workdays;
            }

            cal.add(5, 1);
        }

        return workdays;
    }

    public static int getYearDays(Calendar cal) {
        cal.set(6, 1);
        cal.roll(6, -1);
        int yearDays = cal.get(6);
        return yearDays;
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(2);
        cal.setTime(date);
        cal.set(7, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static Date getFirstDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        cal.setTime(getStartOfDay(new Date()));
        cal.set(7, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static Date getEndOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(1);
        cal.setTime(getEndOfDay(new Date()));
        cal.add(4, 1);
        cal.set(7, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(2);
        cal.setTime(new Date());
        cal.set(7, cal.getFirstDayOfWeek() + 6);
        return cal.getTime();
    }

    public static Date getStartOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        cal.set(7, 2);
        cal.add(4, -1);
        return cal.getTime();
    }

    public static Date getEndOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMaximum(11));
        cal.set(12, cal.getMaximum(12));
        cal.set(13, cal.getMaximum(13));
        cal.set(14, cal.getMaximum(14));
        cal.set(7, 1);
        return cal.getTime();
    }

    public static Date getEndOfLastWed(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dateNum = cal.get(7);
        if (dateNum < 5) {
            cal.add(4, -1);
        }

        cal.set(11, cal.getMaximum(11));
        cal.set(12, cal.getMaximum(12));
        cal.set(13, cal.getMaximum(13));
        cal.set(14, cal.getMaximum(14));
        cal.set(7, 4);
        return cal.getTime();
    }

    public static Date getStartOfLastThu(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dateNum = cal.get(7);
        if (dateNum < 5) {
            cal.add(4, -2);
        } else {
            cal.add(4, -1);
        }

        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        cal.set(7, 5);
        return cal.getTime();
    }

    public static Date getStartOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        cal.set(6, cal.getMinimum(6));
        return cal.getTime();
    }

    public static Date getEndSecondOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getEndOfDay(new Date()));
        cal.set(6, cal.getActualMaximum(6));
        return cal.getTime();
    }

    public static String getNextMonth0fDay(Date date, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.add(2, 2);
        cal.add(5, -1);
        return formatDateTime(cal.getTime(), format);
    }

    public static long getRecentTimeVal() {
        long millisecondsIn38 = 1198368000L;
        return System.currentTimeMillis() / 1000L - millisecondsIn38;
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        return cal.get(7);
    }

    public static Date getNextMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        int dayOfWeek = cal.get(7);
        cal.add(5, (7 - dayOfWeek + 2) % 7);
        return cal.getTime();
    }

    public static int getHourOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        return cal.get(11);
    }

    public static Long getRemainingSeconds() {
        return timeSub(new Date(), getEndOfDay(new Date()));
    }

    public static int getDiscrepantDays(Date dateStart, Date dateEnd) {
        return (int)((dateEnd.getTime() - dateStart.getTime()) / 1000L / 60L / 60L / 24L);
    }
}

