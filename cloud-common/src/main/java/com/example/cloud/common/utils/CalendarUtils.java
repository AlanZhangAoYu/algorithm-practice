package com.example.cloud.common.utils;

import com.example.cloud.common.exception.ServiceException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * @Author candy33
 * @Date 2021/12/31 10:31
 * @ClassName com.example.cloud.common.utils
 **/
public final class CalendarUtils {

    public static final CalendarUtils INSTANCE = new CalendarUtils(Calendar.getInstance());

    private final Calendar calendar;

    private CalendarUtils(Calendar calendar) {
        super();
        this.calendar = Objects.requireNonNull(calendar, "calendar");
    }

    /**
     * 获取今天是本月的多少号
     * @return
     */
    public int getDayOfMonth() {
        return this.getDayOfMonth(null);
    }

    /**
     * 获取今天是本月的多少号
     * @return
     */
    public int getDayOfMonth(Date date) {
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前月
     * @return
     */
    public int getMonth() {
        return this.getMonth(null);
    }

    /**
     * 获取当前月
     * @return
     */
    public int getMonth(Date date) {
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前年
     * @return
     */
    public int getYear() {
        return getYear(null);
    }
    /**
     * 获取当前年
     * @return
     */
    public int getYear(Date date) {
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间在今年的天数
     * @return
     */
    public int getDayOfYear() {
        return this.getDayOfYear(null);
    }

    /**
     * 获取指定时间在今年的天数
     * @param date 时间
     * @return
     */
    public int getDayOfYear(Date date) {
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取当天是在本周的周几，下标开始为1表示周末，下标7表示周六
     * @return
     */
    public int getDayOfWeek() {
        return this.getDayOfWeek(null);
    }
    /**
     * 获取当天是在本周的周几，下标开始为1表示周末，下标7表示周六
     * @param date 时间
     * @return
     */
    public int getDayOfWeek(Date date) {
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据指定时间获取天数
     * @param date 时间
     * @return
     */
    public int getCountOfMonth(Date date) {
        calendar.setTime(date);
        return this.getCountOfMonth(this.getYear(), this.getMonth() + 1);
    }

    /**
     * 根据指定年月获取月份天数
     * @param year 年
     * @param month 月
     * @return
     */
    public int getCountOfMonth(int year, int month) {
        switch (month) {
            case 2:
                int x = year % 4;
                int y = year % 100;
                int z = year % 400;
                if (x == 0) {
                    if (y != 0) {
                        return 29;
                    } else {
                        if (z == 0) {
                            return 29;
                        } else {
                            return 28;
                        }
                    }
                } else {
                    return 28;
                }
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                throw new ServiceException("不存在" + (month + 1) + "月");
        }
    }

    /**
     * 设定传入时间的时分秒为0
     * @param date
     */
    public void sumDateZero(Date date) {
        this.setTime(date, Calendar.HOUR_OF_DAY, 0);
        this.setTime(date, Calendar.MINUTE, 0);
        this.setTime(date, Calendar.SECOND, 0);
        this.setTime(date, Calendar.MILLISECOND, 0);
    }

    /**
     * 传入时间增减天数
     * @param date 时间
     * @param day 增减的天数
     */
    public void sumDay(Date date, int day) {
        this.sumTime(date, Calendar.DATE, day);
    }

    /**
     * 传入时间增减小时
     * @param date 时间
     * @param hour 增减的小时数
     */
    public void sumHour(Date date, int hour) {
        this.sumTime(date, Calendar.HOUR, hour);
    }

    /**
     * 传入时间增减分钟
     * @param date 时间
     * @param minute 增减的分钟数
     */
    public void sumMinute(Date date, int minute) {
        this.sumTime(date, Calendar.MINUTE, minute);
    }

    /**
     * 传入时间增减秒数
     * @param date 时间
     * @param second 秒数
     */
    public void sumSecond(Date date, int second) {
        this.sumTime(date, Calendar.SECOND, second);
    }

    /**
     * 传入年月日获取指定日期的00:00:00
     * @param year 年
     * @param month 月
     * @param day 日
     * @return
     */
    public Date getDate(int year, int month, int day) {
        return this.getDateTime(year, month, day, 0, 0, 0);
    }

    /**
     * 传入年月日时分秒获取指定时间
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return
     */
    public Date getDateTime(int year, int month, int day, int hour, int minute, int second) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置指定时间的年
     * @param date 时间
     * @param year 年
     */
    public void setYear(Date date, int year) {
        this.setTime(date, Calendar.YEAR, year);
    }

    /**
     * 设置指定时间的月
     * @param date 时间
     * @param month 月
     */
    public void setMonth(Date date, int month) {
        this.setTime(date, Calendar.MONTH, month + 1);
    }

    /**
     * 设置指定时间的日
     * @param date 时间
     * @param day 日
     */
    public void setDay(Date date, int day) {
        this.setTime(date, Calendar.DAY_OF_MONTH, day);
    }

    /**
     * 设置指定时间的小时
     * @param date 时间
     * @param hour 小时
     */
    public void setHour(Date date, int hour) {
        this.setTime(date, Calendar.HOUR_OF_DAY, hour);
    }

    /**
     * 设置指定时间的分钟
     * @param date 时间
     * @param minute 分钟
     */
    public void setMinute(Date date, int minute) {
        this.setTime(date, Calendar.MINUTE, minute);
    }

    /**
     * 设置指定时间的秒
     * @param date 时间
     * @param second 秒数
     */
    public void setSecond(Date date, int second) {
        this.setTime(date, Calendar.SECOND, second);
    }

    /**
     * 设置指定时间的毫秒
     * @param date 时间
     * @param millisecond 毫秒数
     */
    public void setMillisecond(Date date, int millisecond) {
        this.setTime(date, Calendar.MILLISECOND, millisecond);
    }

    /**
     * 传入时间和类别进行加减
     * @param date 时间
     * @param TYPE 类别
     * @param time 加减的时长
     */
    private void sumTime(Date date, int TYPE, int time) {
        calendar.setTime(date);
        calendar.add(TYPE, time);
        date.setTime(calendar.getTimeInMillis());
    }

    /**
     * 传入时间和类别进行设置
     * @param date 时间
     * @param TYPE 类别
     * @param time 时间
     */
    private void setTime(Date date, int TYPE, int time) {
        calendar.setTime(date);
        calendar.set(TYPE, time);
        date.setTime(calendar.getTimeInMillis());
    }

    /**
     * 获取当天结束还剩余多少秒
     *
     * @return
     */
    public int getCurrentDayRemainSeconds() {
        //获取明天凌晨0点的日期
        Calendar tomorrowDate = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + 1,
                0, 0, 0);
        //返回 明天凌晨0点 和 今天当前时间 的差值（秒数）
        return (int) (tomorrowDate.getTimeInMillis() - calendar.getTimeInMillis()) / 1000;
    }

}
