package com.ice.brother.house;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jdk 1.8 支持的时间
 *
 * @author: ice
 * @create on:2018-04-12 23:36
 */
public class NDateu {

  private static final Logger logger = LoggerFactory.getLogger(NDateu.class);
  //
  public static final long SECOND = 1 * 1000;
  public static final long MINUTE = 60 * SECOND;
  public static final long HOUR = 60 * MINUTE;
  public static final long DAY = 24 * HOUR;
  public static final long WEEK = 7 * DAY;

  /**
   * 获取当前时间.
   */
  public static final String nowTime() {
    LocalDate now = LocalDate.now();
    return now.toString();
  }

  /**
   * 入参格式: Sat Nov 01 14:01:55 CST 2014.
   */
  public static final LocalDateTime parseLocale(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter
          .ofPattern("MM dd HH:mm:ss zzz yyyy", Locale.US);
      return LocalDateTime.parse(date, formatter);
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
  public static final LocalDateTime parseDateyyyyMMdd(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
      return LocalDateTime.parse(date, formatter);
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
  public static final LocalDateTime parseDateyyyy_MM_dd(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      return LocalDateTime.parse(date, formatter);
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
  public static final LocalDateTime parseDateyyyyMMddHH(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
      return LocalDateTime.parse(date, formatter);
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
  public static final LocalDateTime parseDateyyyyMMDDHHmm(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDDHHmm");
      return LocalDateTime.parse(date, formatter);
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
  public static final LocalDateTime parseDateyyyyMMDDHHmmss(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDDHHmmss");
      return LocalDateTime.parse(date, formatter);
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
  public static final LocalDateTime parseDateyyyy_MM_dd_HH_mm_ss(String date) {
    if (date == null) {
      return null;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return LocalDateTime.parse(date, formatter);
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
  public static final String parseDateyyyy_MM_dd(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return NDateu.parseLocalDate(formatter, date);
  }

  /**
   * 返回格式: yyyy-MM.
   */
  public static final String parseDateyyyy_MM(LocalDate localDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
    return NDateu.parseLocalDate(formatter, localDate);
  }

  /**
   * 返回格式:yyyy-MM-dd HH:mm:ss.
   */
  public static final String parseDateyyyy_MM_ddHHmmss(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:yyyy-MM-dd HH:mm.
   */
  public static final String parseDateyyyy_MM_ddHHmm(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:yyyy/MM/dd HH:mm.
   */
  public static final String parseDateyyyyMMddHHmm2(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:yyyy/MM/dd HH:mm:ss.
   */
  public static final String parseDateyyyyMMddHHmmss3(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:yyyyMMdd.
   */
  public static final String parseDateyyyyMMdd(LocalDate localDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    return NDateu.parseLocalDate(formatter, localDate);
  }

  /**
   * 返回格式:yyyyMMddHH.
   */
  public static final String parseDateyyyyMMddHH(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:yyyyMMddHHmmss.
   */
  public static final String parseDateyyyyMMddHHmmss2(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:yyyyMMddHHmm.
   */
  public static final String parseDateyyyyMMddHHmm3(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:MMddHHmmss.
   */
  public static final String parseDateMMddHHmmss(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
    return NDateu.parseLocalDateTime(formatter, localDateTime);
  }

  /**
   * 返回格式:HH:mm:ss.
   */
  public static final String parseHHmmss(LocalTime localTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    return NDateu.parseLocalTime(formatter, localTime);
  }

  /**
   * 返回格式: HH:mm:ss.ms.
   */
  public static final String parseHHmmssms(LocalTime localTime) {
    long ms = localTime.getNano() / 1000000;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    return NDateu.parseLocalTime(formatter, localTime) + "." + (ms > 99 ? ms
        : (ms > 9 ? ("0" + ms) : ("00" + ms)));
  }

  /**
   * 返回格式:yyyy-MM-dd HH:mm:ss.ms.
   */
  public static final String parseDateyyyyMMddHHmmssms(LocalDateTime localDateTime) {
    long ms = localDateTime.getNano() / 1000000;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return NDateu.parseLocalDateTime(formatter, localDateTime) + "."
        + (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
  }

  /**
   * 返回格式:yyyyMMddHHmmss.ms.
   */
  public static final String parseDateyyyyMMddHHmmssms2(LocalDateTime localDateTime) {
    long ms = localDateTime.getNano() / 1000000;
    return NDateu.parseDateyyyyMMddHHmm3(localDateTime) + "."
        + (ms > 99 ? ms : (ms > 9 ? ("0" + ms) : ("00" + ms)));
  }

  /**
   * 置为凌晨00:00:00 000.
   */
  public static final LocalDateTime set000000(LocalDateTime localDateTime) {
    LocalDateTime dateTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
        localDateTime.getDayOfMonth(), 0, 0, 0);
    return dateTime;
  }

  /**
   * 当前时间的hour, 小于10时前面补零.
   */
  public static final String hour(LocalTime localTime) {
    int hour = localTime.getHour();
    return hour > 9 ? hour + "" : "0" + hour;
  }

  /**
   * yyyymmdd整数形式.
   */
  public static final int yyyymmdd(LocalDate localDate) {
    return Integer
        .parseInt(localDate.getYear() + "" + localDate.getMonth() + "" + localDate.getDayOfMonth());
  }

  /**
   * yyyymm整数形式.
   */
  public static final int yyyymm(LocalDate date) {
    return Integer.parseInt(date.getYear() + "" + date.getMonth());
  }

  public static final String parseLocalDate(DateTimeFormatter formatter, LocalDate localDate) {
    try {
      return localDate == null ? null : localDate.format(formatter);
    } catch (Exception e) {
      return null;
    }
  }

  public static final String parseLocalTime(DateTimeFormatter formatter, LocalTime localTime) {
    try {
      return localTime == null ? null : localTime.format(formatter);
    } catch (Exception e) {
      return null;
    }
  }

  public static final String parseLocalDateTime(DateTimeFormatter formatter,
      LocalDateTime localDateTime) {
    try {
      return localDateTime == null ? null : localDateTime.format(formatter);
    } catch (Exception e) {
      return null;
    }
  }
}
