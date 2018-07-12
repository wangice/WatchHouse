package com.ice.brother.house;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ice
 * @create on:2018-04-12 23:25
 */
public class ODateu {

  private static final Logger logger = LoggerFactory.getLogger(ODateu.class);

  public static final long SECOND = 1 * 1000;
  public static final long MINUTE = 60 * SECOND;
  public static final long HOUR = 60 * MINUTE;
  public static final long DAY = 24 * HOUR;
  public static final long WEEK = 7 * DAY;
  public static final TimeZone tz = TimeZone.getDefault();

  /**
   * 入参格式: Sat Nov 01 14:01:55 CST 2014.
   */
  public static final Date parseLocale(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 入参格式: yyyy.
   */
  public static final Date parseDateyyyy(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyy").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 入参格式: yyyy-MM.
   */
  public static final Date parseDateyyyy_MM(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyy-MM").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 入参格式: yyyyMMdd.
   */
  public static final Date parseDateyyyyMMdd(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyyMMdd").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 入参格式: yyyy-MM-dd.
   */
  public static final Date parseDateyyyy_MM_dd(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 解析yyyyMMddHH格式的日期.
   */
  public static final Date parseDateyyyyMMddHH(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyyMMddHH").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 解析yyyyMMddHHmm格式的日期.
   */
  public static final Date parseDateyyyyMMddHHmm(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyyMMddHHmm").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 解析yyyyMMddHHmmss格式的日期.
   */
  public static final Date parseDateyyyyMMddHHmmss(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 解析yyyy-MM-dd HH:mm:ss格式的日期.
   */
  public static final Date parseDateyyyy_MM_dd_HH_mm_ss(String date) {
    if (date == null) {
      return null;
    }
    try {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}, date: {}", Misc.trace(e), date);
      }
      return null;
    }
  }

  /**
   * 返回格式: yyyy-MM-dd.
   */
  public static final String parseDateyyyy_MM_dd(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyy-MM-dd"), date);
  }

  /**
   * 返回格式: yyyy-MM.
   */
  public static final String parseDateyyyy_MM(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyy-MM"), date);
  }

  /**
   * 返回格式:yyyy-MM-dd HH:mm:ss.
   */
  public static final String parseDateyyyyMMddHHmmss(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), date);
  }

  /**
   * 返回格式:yyyy/MM/dd HH:mm.
   */
  public static final String parseDateyyyyMMddHHmm2(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyy/MM/dd HH:mm"), date);
  }

  /**
   * 返回格式:yyyyMMdd.
   */
  public static final String parseDateyyyyMMdd(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyyMMdd"), date);
  }

  /**
   * 返回格式:yyyyMMddHH.
   */
  public static final String parseDateyyyyMMddHH(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyyMMddHH"), date);
  }

  /**
   * 返回格式:yyyyMMddHHmmss.
   */
  public static final String parseDateyyyyMMddHHmmss2(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyyMMddHHmmss"), date);
  }

  /**
   * 返回格式:yyyyMMddHHmm.
   */
  public static final String parseDateyyyyMMddHHmm(Date date) {
    return ODateu.parse(new SimpleDateFormat("yyyyMMddHHmm"), date);
  }

  /**
   * 返回格式:MMddHHmmss.
   */
  public static final String parseDateMMddHHmmss(Date date) {
    return ODateu.parse(new SimpleDateFormat("MMddHHmmss"), date);
  }

  /**
   * 返回格式:HH:mm:ss.
   */
  public static final String parseDateHHmmss(Date date) {
    return ODateu.parse(new SimpleDateFormat("HH:mm:ss"), date);
  }

  /**
   * 返回格式: HH:mm:ss.ms.
   */
  public static final String parseDateHHmmssms(Date date) {
    long ms = date.getTime() % 1000;
    return ODateu.parse(new SimpleDateFormat("HH:mm:ss"), date) + "."
        + (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
  }

  /**
   * 返回格式:yyyy-MM-dd HH:mm:ss.ms.
   */
  public static final String parseDateyyyyMMddHHmmssms(Date date) {
    long ms = date.getTime() % 1000;
    return ODateu.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), date) + "."
        + (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
  }

  /**
   * 返回格式:yyyyMMddHHmmssms.
   */
  public static final String parseDateyyyyMMddHHmmssms2(Date date) {
    long ms = date.getTime() % 1000;
    return ODateu.parseDateyyyyMMddHHmmss2(date) + (ms > 99 ? ms
        : (ms > 9 ? ("0" + ms) : ("00" + ms)));
  }

  /**
   * 置为凌晨00:00:00 000,Calendar提供的set函数.
   */
  public static final Date set000000(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(date.getTime() - (date.getTime() % 1000));
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0,
        0);
    return cal.getTime();
  }

  /**
   * 当前时间的hour, 小于10时前面补零.
   */
  public static final String hour(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    return hour > 9 ? hour + "" : "0" + hour;
  }

  /**
   * 返回秒(0 ~ 59).
   */
  public static final int secondInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.SECOND);
  }

  /**
   * 返回分钟(0 ~ 59).
   */
  public static final int minuteInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.MINUTE);
  }

  /**
   * 返回小时(0 ~ 23).
   */
  public static final int hourInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.HOUR_OF_DAY);
  }

  /**
   * 返回天.
   */
  public static final int dayInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 返回星期.
   */
  public static final int weekInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.WEEK_OF_YEAR);
  }

  /**
   * 星期几? (周期一返回1, 星期天返回7).
   */
  public static final int week(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int week = cal.get(Calendar.DAY_OF_WEEK);
    week -= 1;
    return week < 1 ? 7 : week;
  }

  /**
   * 返回月份.
   */
  public static final int monthInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.MONTH) + 1;
  }

  /**
   * 返回年份.
   */
  public static final int yearInt(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR);
  }

  /**
   * yyyymm整数形式.
   */
  public static final int yyyymm(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR) * 100 + (cal.get(Calendar.MONTH) + 1);
  }

  /**
   * yyyymmdd整数形式.
   */
  public static final int yyyymmdd(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal
        .get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 返回这个月的总天数.
   */
  public static final int monthDays(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  /**
   * 返回这个月的最后一天(时分秒跟随).
   */
  public static final Date montheLastDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar ret = Calendar.getInstance();
    ret.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.getActualMaximum(Calendar.DAY_OF_MONTH),
        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
    return ret.getTime();
  }

  /**
   * 返回这个月的第一天(00:00:00).
   */
  public static final Date monthFirstDay000000(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar ret = Calendar.getInstance();
    ret.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
    return ret.getTime();
  }

  /**
   * 返回这个月的最后一天(23:59:59).
   */
  public static final Date monthLastDay235959(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar ret = Calendar.getInstance();
    ret.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
        cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59,
        59);
    return ret.getTime();
  }

  /**
   * 返回这一年的第一天(00:00:00).
   */
  public static final Date yearFirstDay000000(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar ret = Calendar.getInstance();
    ret.set(cal.get(Calendar.YEAR), cal.getActualMinimum(Calendar.MONTH),
        cal.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
    return ret.getTime();
  }

  /**
   * 返回这一年的最后一天(23:59:59).
   */
  public static final Date yearLastDay235959(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar ret = Calendar.getInstance();
    ret.set(cal.get(Calendar.YEAR), cal.getActualMaximum(Calendar.MONTH),
        cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
    return ret.getTime();
  }

  /**
   * 获取当前系统时区.
   */
  public static final int getTimezone() {
    return (int) (ODateu.tz.getRawOffset() / ODateu.HOUR);
  }

  /**
   * 将本地时间转换成指定时区的时间.
   */
  public static final long changeLocalTimeZone(long ts /* 本地时间, 毫秒. */,
      int gmt /* 指定时区偏移, 小时 . */) {
    return (ts - ODateu.tz.getRawOffset() /* 回归零时区. */) + (gmt * ODateu.HOUR);
  }

  /**
   * 将本地时间转换成指定时区的时间.
   */
  public static final Date changeLocalTimeZone2date(long ts /* 本地时间, 毫秒. */,
      int gmt /* 指定时区偏移, 小时 . */) {
    return new Date(ODateu.changeLocalTimeZone(ts, gmt));
  }

  /**
   * 返回当前时间在零时区的绝对时间.
   */
  public static final Date nowGmt0() {
    return new Date(System.currentTimeMillis() - ODateu.tz.getRawOffset() /* 回归零时区. */);
  }

  /**
   * 将指定GM+0时间回归到GMT+x.
   */
  public static final Date gotoGmtxOld(Date date /* 具有gmt0时区的绝对时间. */, int gmt /* 要返回的时区. */) {
    return new Date(date.getTime() + gmt * ODateu.HOUR);
  }

  /**
   * 将指定时间回归到GMT+0.
   */
  public static final Date gotoGmt0Old(Date date /* 具有gmt时区的绝对时间. */, int gmt /* date的时区. */) {
    return new Date((date.getTime() - gmt * ODateu.HOUR));
  }

  /**
   * 将本地时区绝对时间转换成目标时区的绝对时间.
   */
  public static final Date gotoGmtx(long ts /* 本时绝对时间. */, int gmtSec /* 要返回的时区(秒) */) {
    return new Date(
        (ts - ODateu.tz.getRawOffset() /* 去零时区. */) + (gmtSec * ODateu.SECOND /* 去目标时区. */));
  }

  /**
   * 将指定GMT+x时间回归到GMT+0.
   */
  public static final Date gmtxGoto0(Date date /* 具有gmtSec时区的绝对时间. */, int gmtSec /* date的时区. */) {
    return new Date((date.getTime() - gmtSec * ODateu.SECOND));
  }

  /**
   * 将指定GM+0时间回归到GMT+x.
   */
  public static final Date gmt0Gotox(Date date /* 具有gmt0时区的绝对时间. */, int gmtSec /* 要返回的时区(秒). */) {
    return new Date(date.getTime() + gmtSec * ODateu.SECOND);
  }

  /**
   * 本地时间去零时区.
   */
  public static final Date gotoGmt0(Date date /* 具有本地时区的时间 */) {
    return new Date(date.getTime() - ODateu.tz.getRawOffset());
  }

  /**
   * 零时区时间去本地时区.
   */
  public static final Date gotoLocal(Date date/* 具有0时区的时间. */) {
    return new Date(date.getTime() + ODateu.tz.getRawOffset());
  }

  /**
   * 判断两个日期是否在同一天.
   */
  public static final boolean isSameDay(Date arg0, Date arg1) {
    return (ODateu.yearInt(arg0) == ODateu.yearInt(arg1)) && //
        (ODateu.monthInt(arg0) == ODateu.monthInt(arg1)) && //
        (ODateu.dayInt(arg0) == ODateu.dayInt(arg1));
  }

  /**
   * 构造一个给定的时间.
   */
  public static final Date createDate(int year, int month, int day, int hourOfDay, int minute,
      int second) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month, day, hourOfDay, minute, second);
    return cal.getTime();
  }

  /**
   * 时间滚动, 按秒钟, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfSecond(Date date, int amount, boolean up) {
    return up ? new Date(date.getTime() + ((long) amount) * ODateu.SECOND)
        : new Date(date.getTime() - ((long) amount) * ODateu.SECOND);
  }

  /**
   * 时间滚动, 按分钟, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfMinute(Date date, int amount, boolean up) {
    return up ? new Date(date.getTime() + ((long) amount) * ODateu.MINUTE)
        : new Date(date.getTime() - ((long) amount) * ODateu.MINUTE);
  }

  /**
   * 时间滚动, 按小时, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfHour(Date date, int amount, boolean up) {
    return up ? new Date(date.getTime() + ((long) amount) * ODateu.HOUR)
        : new Date(date.getTime() - ((long) amount) * ODateu.HOUR);
  }

  /**
   * 时间滚动, 按天, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfDay(Date date, int amount, boolean up) {
    return up ? new Date(date.getTime() + ((long) amount) * ODateu.DAY)
        : new Date(date.getTime() - ((long) amount) * ODateu.DAY);
  }

  /**
   * 时间滚动, 按月, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfMonth(Date date, boolean up) {
    Calendar ca = Calendar.getInstance();
    ca.setTime(date);
    ca.roll(Calendar.MONTH, up);
    int m = ODateu.monthInt(date);
    if (m == 1 && !up) {
      ca.roll(Calendar.YEAR, false);
    }
    if (m == 12 && up) {
      ca.roll(Calendar.YEAR, true);
    }
    return ca.getTime();
  }

  /**
   * 时间滚动, 按年, up == true向今后滚动, 否则向以前滚动.
   */
  public static final Date dateRollOfYear(Date date, boolean up) {
    Calendar ca = Calendar.getInstance();
    ca.setTime(date);
    ca.roll(Calendar.YEAR, up);
    return ca.getTime();
  }

  /**
   * 清除分钟.
   */
  public static final Date clearMinute(Date date) {
    return new Date(date.getTime() - (date.getTime() % ODateu.HOUR));
  }

  /**
   * 清除小时.
   */
  public static final Date clearHour(Date date) {
    return ODateu.set000000(date);
  }

  /**
   * 秒转换为毫秒, 出现这个函数的原因时, 当前时间的秒数 * 1000后总是整数(4字节)溢出, 此函数则可避免出错.
   */
  public static final long sec2msec(long sec) {
    return sec * 1000L;
  }

  /**
   * 按格式解析日期.
   */
  private static final String parse(SimpleDateFormat format, Date date) {
    try {
      return date == null ? null : format.format(date);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}", Misc.trace(e));
      }
      return null;
    }
  }
}
