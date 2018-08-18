package com.ice.brother.house;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ice
 * @create on:2018-04-12 23:31
 */
public class Misc {

  private static final Logger logger = LoggerFactory.getLogger(Misc.class);
  //
  public static final String UTF_8 = "UTF-8";
  public static final String GBK = "GBK";
  public static final String ISO_8859_1 = "ISO-8859-1";
  public static final String COMMA = ",";
  public static final String COMMA2 = ";";
  public static final String SPACE = " ";
  public static final String LINE = "\n";
  public static final String LINE_R_N = "\r\n";
  public static final String _LINE = "_";
  public static final String UNIX_FILE_SEPARATOR = "/";
  public static final int SUCCESS = 0;
  public static final int FAILED = -1;
  public static final long procup = System.currentTimeMillis();
  private static char __0aA__[] = {'_', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
      'c', 'd', 'e',
      'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
      'y', 'z',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
      'T', 'U',
      'V', 'W', 'X', 'Y', 'Z'};
  private static final DecimalFormat df = new DecimalFormat("#0.00");
  private static final PrintStream out = new PrintStream(System.out);
  private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  public static final Gson gson = new GsonBuilder().serializeNulls()
      .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

  /**
   * 从标准输入读取一行.
   */
  public static final String readLine() {
    try {
      return Misc.in.readLine();
    } catch (IOException e) {
      return null;
    }
  }

  /**
   * 去除首尾空格.
   */
  public static final String trim(String arg) {
    return arg == null ? null : ("".equals(arg.trim()) ? null : arg.trim());
  }

  /**
   * 异常时返回零, 否则值总是 >=0.
   */
  public static final long forceLongO(String arg) {
    try {
      long value = Long.parseLong(Misc.trim(arg));
      return value < 0 ? 0 : value;
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * 异常时返回零, 否则值总是 >=0.00f.
   */
  public static final double forceDouble0(String arg) {
    try {
      double value = Double.parseDouble(Misc.trim(arg));
      return value < 0.00f ? 0.00f : value;
    } catch (Exception e) {
      return 0.00f;
    }
  }

  /**
   * 异常时返回-1.
   */
  public static final int int_1(String arg) {
    try {
      return Integer.parseInt(Misc.trim(arg));
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * 异常时返回-1, 否则值总是 >=-1.
   */
  public static final int forceInt_1(String arg) {
    try {
      int value = Integer.parseInt(Misc.trim(arg));
      return value < -1 ? -1 : value;
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * 异常时返回零.
   */
  public static final int int0(String arg) {
    try {
      return Integer.parseInt(Misc.trim(arg));
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * 异常时返回零, 否则值总是 >=0.
   */
  public static final int forceInt0(String arg) {
    try {
      int value = Integer.parseInt(Misc.trim(arg));
      return value < 0 ? 0 : value;
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * 异常时返回1.
   */
  public static final int int1(String arg) {
    try {
      return Integer.parseInt(Misc.trim(arg));
    } catch (Exception e) {
      return 1;
    }
  }

  /**
   * 异常时返回1, 否则值总是 >= 1
   */
  public static final int forceInt1(String arg) {
    try {
      int value = Integer.parseInt(Misc.trim(arg));
      return value < 1 ? 1 : value;
    } catch (Exception e) {
      return 1;
    }
  }

  /**
   * 异常返回Null.
   */
  public static final Integer forceInteger(String arg) {
    try {
      Integer value = Integer.parseInt(Misc.trim(arg));
      return value;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 异常时返回0, 否则总是 >= 0.
   */
  public static final float forceFloat0(String arg) {
    try {
      float value = Float.parseFloat(Misc.trim(arg));
      return value < 0 ? 0 : value;
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * 异常时返回1.0, 否则总是 >= 1.0.
   */
  public static final float forceFloat1(String arg) {
    try {
      float value = Float.parseFloat(Misc.trim(arg));
      return value < 1.0f ? 1.0f : value;
    } catch (Exception e) {
      return 1.0f;
    }
  }

  /**
   * 异常时返回-1, 否则值总是 >= -1
   */
  public static final float forceFloat_1(String arg) {
    try {
      float value = Float.parseFloat(Misc.trim(arg));
      return value < -1.00f ? -1.00f : value;
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * 异常时返回0.
   */
  public static final float float0(String arg) {
    try {
      return Float.parseFloat(Misc.trim(arg));
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * 将逗号表达式分隔的整形字符串转换成字节数组, 如:"1,2,3,4,5", 异常时返回null.
   */
  public static final byte[] parseByteArr(String str) {
    int arr[] = Misc.parseIntArr(str);
    if (arr == null || arr.length < 1) {
      return null;
    }
    byte by[] = new byte[arr.length];
    for (int i = 0; i < arr.length; ++i) {
      by[i] = (byte) arr[i];
    }
    return by;
  }

  /**
   * 将逗号表达式分隔的整形字符串转换成整形数组, 如:"1,2,3,4,5", 同时也支持"1,2,3,4,5,", 异常时返回null.
   */
  public static final int[] parseIntArr(String str) {
    try {
      String arr[] = str.split(Misc.COMMA);
      if (arr == null || arr.length < 1) {
        return null;
      }
      int ia[] = new int[arr.length];
      for (int i = 0; i < ia.length; ++i) {
        ia[i] = Integer.parseInt(Misc.trim(arr[i]));
      }
      return ia;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 将逗号表达式分隔的字符串转换成字符串数组, 如:"1,2,3,4,5", 异常时返回null.
   */
  public static final String[] parseStrArr(String str) {
    try {
      String arr[] = str.split(Misc.COMMA);
      if (arr == null || arr.length < 1) {
        return null;
      }
      for (int i = 0; i < arr.length; ++i) {
        arr[i] = Misc.trim(arr[i]);
        if (arr[i] == null) {
          return null;
        }
      }
      return arr;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 将逗号表达式分隔的字符串转换成字符串数组(List), 如:"1,2,3,4,5", 异常时返回null.
   */
  public static final List<String> parseStr2List(String str) {
    try {
      String arr[] = str.split(Misc.COMMA);
      if (arr == null || arr.length < 1) {
        return null;
      }
      ArrayList<String> list = new ArrayList<>();
      for (int i = 0; i < arr.length; ++i) {
        String s = Misc.trim(arr[i]);
        if (s == null) {
          return null;
        }
        list.add(s);
      }
      return list.isEmpty() ? null : list;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 整形数组转换成以逗号分隔的字符串.
   */
  public static final String intArr2String(int arr[]) {
    StringBuilder strb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      strb.append(arr[i]).append(Misc.COMMA);
    }
    return strb.substring(0, strb.length() - 1);
  }

  /**
   * 整形数组转换成以逗号分隔的字符串.
   */
  public static final String intArr2String(ArrayList<Integer> arr) {
    StringBuilder strb = new StringBuilder();
    for (int i = 0; i < arr.size(); i++) {
      strb.append(arr.get(i)).append(Misc.COMMA);
    }
    return strb.substring(0, strb.length() - 1);
  }

  /**
   * 字符串数据转换成以逗号分隔的字符串(带单引号).
   */
  public static final String strArr2StringWithSQ(String[] arr) {
    StringBuilder strb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      strb.append("'").append(arr[i]).append("',");
    }
    return strb.substring(0, strb.length() - 1);
  }

  /**
   * 字符串数据转换成以逗号分隔的字符串(带单引号).
   */
  public static final String strArr2StringWithSQ(ArrayList<String> arr) {
    StringBuilder strb = new StringBuilder();
    for (int i = 0; i < arr.size(); i++) {
      strb.append("'").append(arr.get(i)).append("',");
    }
    return strb.substring(0, strb.length() - 1);
  }

  /**
   * 判断字符串是否为NULL.
   */
  public static final boolean isNull(String arg) {
    return Misc.trim(arg) == null;
  }

  /**
   * parse为一个byte.
   */
  public static final Byte parseByte(String arg) {
    try {
      return Byte.parseByte(trim(arg));
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 是不是一串数字.
   */
  public static final boolean isDigital(String arg) {
    arg = trim(arg);
    if (arg == null) {
      return false;
    }
    char[] array = arg.toCharArray();
    for (char chr : array) {
      if (!Character.isDigit(chr)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 是不是一个数字.
   */
  public static final boolean isDigital(byte by) {
    return by >= 0x30 && by <= 0x39;
  }

  /**
   * 是不是一个字母.
   */
  public static final boolean isLetter(byte by) {
    return (by >= 0x61 && by <= 0x7A) || (by >= 0x41 && by <= 0x5A);
  }

  /**
   * 是不是一串可读的ASCII码(0 ~ 9, a ~ Z).
   */
  public static final boolean isDigitalOrLetter(byte by[]) {
    for (int i = 0; i < by.length; ++i) {
      if (!Misc.isDigital(by[i]) && !Misc.isLetter(by[i])) {
        return false;
      }
    }
    return by.length > 0;
  }

  /**
   * 是不是一串可读的ASCII码(0 ~ 9, a ~ Z).
   */
  public static final boolean isDigitalOrLetter(byte by[], int ofst, int len) {
    for (int i = ofst; i < len; ++i) {
      if (!Misc.isDigital(by[i]) && !Misc.isLetter(by[i])) {
        return false;
      }
    }
    return len > 0;
  }

  /**
   * 是不是一串可读的ASCII码(0x20 ~ 0x7E).
   */
  public static final boolean isReadableAscii(byte by[]) {
    for (int i = 0; i < by.length; ++i) {
      if (by[i] < 0x20 || by[i] > 0x7E) {
        return false;
      }
    }
    return by.length > 0;
  }

  /**
   * 替代c的printf.
   */
  public static final void printf(String format, Object... args) {
    Misc.out.printf(format, args);
  }

  /**
   * 打印byte[]到标准输出.
   */
  public static final void printf(byte by[]) {
    Misc.printf("%s\n", Net.printBytes(by, 0, by.length));
  }

  /**
   * 打印byte[]到标准输出.
   */
  public static final void printf(byte by[], int offset, int length) {
    Misc.printf(Net.printBytes(by, offset, length));
  }

  /**
   * 替代c的printf, 将打印的结果以字符串方式返回.
   */
  public static final String printf2Str(String format, Object... args) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(bos);
    ps.printf(format, args);
    return bos.toString();
  }

  /**
   * 将字节流转换成十六进制字符串.
   */
  public static final String printBytes(byte by[]) {
    return Net.printBytes(by, 0, by.length);
  }

  /**
   * 将字节流转换成十六进制字符串.
   */
  public static final String printBytes(byte by[], int offset, int length) {
    return Net.printBytes(by, offset, length);
  }

  /**
   * format to #0.00.
   */
  public static final String decimalformat(double val) {
    return Misc.df.format(val);
  }

  /**
   * 判断是不是一个可能合法的email格式.
   */
  public static final boolean isEmail(String email) {
    if (email == null) {
      return false;
    }
    byte by[] = email.getBytes();
    for (int i = 0; i < by.length; i++) {
      if (/**/by[i] != 0x2D /* - */ && //
          by[i] != 0x2E /* . */ && //
          by[i] != 0x40 /* @ */ && //
          by[i] != 0x5F /* _ */ && //
          (by[i] < 0x30 || by[i] > 0x39 /* 0 ~ 9 */) && //
          (by[i] < 0x61 || by[i] > 0x7A /* a ~ z */) && //
          (by[i] < 0x41 || by[i] > 0x5A /* A ~ Z */)) {
        return false;
      }
    }
    return true;
  }

  /**
   * ISO-8859-1 to UTF-8.
   */
  public static final String iso2utf8(String str) {
    try {
      return Misc.trim(new String(str.getBytes(Misc.ISO_8859_1), Misc.UTF_8));
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * ISO-8859-1 to GBK.
   */
  public static final String iso2gbk(String str) {
    try {
      return Misc.trim(new String(str.getBytes(Misc.ISO_8859_1), Misc.GBK));
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * ISO-8859-1 to GBK.
   */
  public static final String iso2gbk(byte by[], int ofst, int len) {
    try {
      return Misc.trim(new String(by, ofst, len, Misc.GBK));
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 将给定值(val)以一个基准的百分比(bp)进行一定百分比的随机浮动(f).
   */
  public static final double randPercent(double val, float bp, float f) {
    /*
     * 例: val = 700, bp = 0.7, 即70%, f = 0.2, 即20%, 那么产生的值早小为700 * 0.7 = 490, 最大值为
     * 490 + 700 * 0.2 = 630.
     */
    if (val < 0.00001) {
      return val;
    }
    double bv = val * bp;
    double fv = bv + (val * f);
    return ThreadLocalRandom.current().nextDouble(bv, fv);
  }

  /**
   * 生成一个随机数(正长整形).
   */
  public static final long randLang() {
    return ThreadLocalRandom.current().nextLong() & 0x7FFFFFFFFFFFFFFFL;
  }

  /**
   * 生成一个随机数(正整形).
   */
  public static final int randInt() {
    return (int) (ThreadLocalRandom.current().nextLong() & 0x7FFFFFFF);
  }

  /**
   * 产生一个128位(16个字节)随机key, 内容为随机的 0 ~ 9, a ~ z, A ~ Z, _.
   */
  public static final String gen0aAkey128() {
    byte by[] = new byte[16];
    for (int i = 0; i < 4; ++i) {
      int x = Misc.randInt();
      by[4 * i + 0] = (byte) __0aA__[(x) % __0aA__.length];
      by[4 * i + 1] = (byte) __0aA__[(x >> 0x08) % __0aA__.length];
      by[4 * i + 2] = (byte) __0aA__[(x >> 0x10) % __0aA__.length];
      by[4 * i + 3] = (byte) __0aA__[(x >> 0x18) % __0aA__.length];
    }
    return new String(by);
  }

  /**
   * 产生一个256位(32个字节)随机key, 内容为随机的 0 ~ 9, a ~ z, A ~ Z, _.
   */
  public static final String gen0aAkey256() {
    byte by[] = new byte[32];
    for (int i = 0; i < 8; ++i) {
      int x = Misc.randInt();
      by[4 * i + 0] = (byte) __0aA__[(x) % __0aA__.length];
      by[4 * i + 1] = (byte) __0aA__[(x >> 0x08) % __0aA__.length];
      by[4 * i + 2] = (byte) __0aA__[(x >> 0x10) % __0aA__.length];
      by[4 * i + 3] = (byte) __0aA__[(x >> 0x18) % __0aA__.length];
    }
    return new String(by);
  }

  /**
   * 产生一个512位(64个字节)随机key, 内容为随机的 0 ~ 9, a ~ z, A ~ Z, _.
   */
  public static final String gen0aAkey512() {
    byte by[] = new byte[64];
    for (int i = 0; i < 8; ++i) {
      int x = Misc.randInt();
      by[8 * i + 0] = (byte) __0aA__[(x) % __0aA__.length];
      by[8 * i + 1] = (byte) __0aA__[(x >> 0x08) % __0aA__.length];
      by[8 * i + 2] = (byte) __0aA__[(x >> 0x10) % __0aA__.length];
      by[8 * i + 3] = (byte) __0aA__[(x >> 0x18) % __0aA__.length];
      x = Misc.randInt();
      by[8 * i + 4] = (byte) __0aA__[(x) % __0aA__.length];
      by[8 * i + 5] = (byte) __0aA__[(x >> 0x08) % __0aA__.length];
      by[8 * i + 6] = (byte) __0aA__[(x >> 0x10) % __0aA__.length];
      by[8 * i + 7] = (byte) __0aA__[(x >> 0x18) % __0aA__.length];
    }
    return new String(by);
  }

  /**
   * 测试bit位是否被设置(一个byte8个bit, 按by的索引依次前向排列, 也就是说第二个字节的第一个bit算是第9个bit).
   */
  public static final boolean bitset(byte by[], int bit) {
    return ((by[(bit / 8)] >> (bit % 8)) & 0x01) == 1;
  }

  /**
   * sleep毫秒.
   */
  public static final void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
    }
  }

  /**
   * 导致当前线程挂起.
   */
  public static final void hold() {
    try {
      Thread.currentThread().join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 按行加载整个文件.
   */
  public static final ArrayList<String> loadFileLines(String path) {
    return Misc.loadFileLines(new File(path));
  }

  /**
   * 按行加载整个文件.
   */
  public static final ArrayList<String> loadFileLines(File file) {
    RandomAccessFile raf = null;
    try {
      ArrayList<String> lines = new ArrayList<>();
      raf = new RandomAccessFile(file, "r");
      String line = raf.readLine();
      while (line != null) {
        lines.add(line);
        line = raf.readLine();
      }
      return lines;
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    } finally {
      Misc.closeRaf(raf);
    }
  }

  /**
   * 加载整个文件.
   */
  public static final byte[] loadFile(String path) {
    FileInputStream fis = null;
    DataInputStream dis = null;
    try {
      File f = new File(path);
      fis = new FileInputStream(f);
      dis = new DataInputStream(fis);
      byte by[] = new byte[(int) f.length()];
      dis.readFully(by);
      return by;
    } catch (Exception e) {
      return null;
    } finally {
      Misc.closeInputStream(dis);
      Misc.closeInputStream(fis);
    }
  }

  /**
   * 加载文件夹下的所有文件(不包括目录).
   */
  public static final File[] loadFiles(String dir) {
    ArrayList<File> arr = new ArrayList<>();
    Misc.__loadFiles__(new File(dir), arr);
    if (arr.size() < 1) {
      return null;
    }
    File fs[] = new File[arr.size()];
    for (int i = 0; i < fs.length; ++i) {
      fs[i] = arr.get(i);
    }
    return fs;
  }

  /**
   * 加载文件夹下的所有文件.
   */
  private static final void __loadFiles__(File file, ArrayList<File> arr) {
    if (file.isDirectory()) {
      File fs[] = file.listFiles();
      for (int i = 0; fs != null && i < fs.length; ++i) {
        Misc.__loadFiles__(fs[i], arr);
      }
    } else {
      arr.add(file);
    }
  }

  /**
   * 文件写入.
   */
  public static final boolean writeFile(String path, byte[] by) {
    try {
      FileOutputStream fos = new FileOutputStream(path);
      fos.write(by);
      fos.flush();
      fos.close();
      return true;
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return false;
    }
  }

  /**
   * 文件是否以指定的后缀名结尾.
   */
  public static final boolean isSuffix(String name, String suffix) {
    int i = name.indexOf(suffix);
    return i < 1 ? false : i == name.length() - suffix.length();
  }

  /**
   * 关闭输入流.
   */
  public static final void closeInputStream(InputStream ins) {
    if (ins != null) {
      try {
        ins.close();
      } catch (IOException e) {
        if (logger.isErrorEnabled()) {
          logger.error("{}", Misc.trace(e));
        }
      }
      ins = null;
    }
  }

  /**
   * 关闭输出流.
   */
  public static final void closeOutputStream(OutputStream ops) {
    if (ops != null) {
      try {

        ops.close();
      } catch (Exception e) {
        if (logger.isErrorEnabled()) {
          logger.error("{}", Misc.trace(e));
        }
      }
      ops = null;
    }
  }

  /**
   * 关闭随机文件读取句柄.
   */
  public static final void closeRaf(RandomAccessFile raf) {
    if (raf != null) {
      try {
        raf.close();
      } catch (Exception e) {
        if (logger.isErrorEnabled()) {
          logger.error("{}", Misc.trace(e));
        }
      }
      raf = null;
    }
  }

  /**
   * 查找一个类.
   */
  @SuppressWarnings("unchecked")
  public static final <T> Class<T> classForName(String cls, Class<T> sup) {
    try {
      Class<?> c = Class.forName(cls);
      return (c.getSuperclass().getName().equals(sup.getName())) ? (Class<T>) c : null;
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * 加载一个类.
   */
  @SuppressWarnings("unchecked")
  public static final <T> Class<T> loadClass(ClassLoader loader, String cls, Class<T> sup) {
    try {
      Class<?> c = loader.loadClass(cls);
      return (c.getSuperclass().getName().equals(sup.getName())) ? (Class<T>) c : null;
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * 以JNI标准格式返回函数的参数列表.
   */
  public static final String getMethodPars(Class<?> cls, String funName) {
    Method m[] = cls.getDeclaredMethods();
    StringBuilder strb = new StringBuilder();
    for (int i = 0; m != null && i < m.length; i++) {
      if (m[i].getName().equals(funName)) {
        Class<?> x[] = m[i].getParameterTypes();
        for (int j = 0; x != null && j < x.length; j++) {
          strb.append("L").append(x[j].getName()).append(";");
        }
      }
    }
    String str = strb.toString();
    if (str.length() < 2) {
      return null;
    }
    return strb.toString().replace('.', '/');
  }

  /**
   * 通过函数名返回函数指针, 不支持重载函数.
   */
  public static final Method findMethodByName(Class<?> cls, String name) {
    Method m[] = cls.getDeclaredMethods();
    for (int i = 0; i < m.length; i++) {
      System.out.println(m[i].getName());
      if (m[i].getName().equals(name)) {
        return m[i];
      }
    }
    return null;
  }

  /**
   * 静态函数调用, 适用于不关心返回值的场景.
   */
  public static final void invoke(Method m, Object... args) {
    try {
      m.invoke(null, args);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
    }
  }

  /**
   * 将对象序列化.
   */
  public static final byte[] serialObject(Object o) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(o);
      return baos.toByteArray();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * 将对象反序列化.
   */
  @SuppressWarnings("unchecked")
  public static final <T> T unSerialObject(byte by[]) {
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(by);
      ObjectInputStream ois = new ObjectInputStream(bais);
      return (T) ois.readObject();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * pb对象打印.
   */
  public static final String pb2str(MessageOrBuilder msg) {
    try {
      return JsonFormat.printer().omittingInsignificantWhitespace().print(msg);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * pb对象打印到标准输出.
   */
  public static final void printPb(Message msg) {
    System.out.println(Misc.pb2str(msg));
  }

  /**
   * 自动强制转换.
   */
  @SuppressWarnings("unchecked")
  public static final <T> T get(Object o) {
    return (T) o;
  }

  /**
   * json字符串转换成对象数组.
   */
  public static final <T> ArrayList<T> json2List(String json, Class<T> cls) {
    try {
      ArrayList<T> arr = new ArrayList<T>();
      JsonArray array = new JsonParser().parse(json).getAsJsonArray();
      array.forEach(v -> arr.add(Misc.gson.fromJson(v, cls)));
      return arr;
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * json字符串转换成对象.
   */
  public static final <T> T json2Obj(String json, Class<T> t) {
    try {
      return Misc.gson.fromJson(json, t);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * 对象转换成json字符串.
   */
  public static final String obj2json(Object o) {
    try {
      return Misc.gson.toJson(o);
    } catch (Exception e) {
      if (logger.isDebugEnabled()) {
        logger.debug("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * json字符串转化Map
   */
  public static final Map<String, Object> json2Map(String json) {
    try {
      return Misc.gson.fromJson(json, HashMap.class);
    } catch (Exception e) {
      logger.debug("{}", Misc.trace(e));
      return null;
    }
  }

  /**
   * 设置系统环境变量.
   */
  public static final void setEnv(String key, Object val) {
    System.setProperty(key, val == null ? "" : val.toString());
  }

  /**
   * 获得环境变量的值.
   */
  public static final String getEnvStr(String key, String def) {
    String str = Misc.trim(System.getProperty(key));
    if (str == null) {
      str = Misc.trim(System.getenv(key));
      return str == null ? def : str;
    }
    return str;
  }

  /**
   * 获得环境变量的值.
   */
  public static final int getEnvInt(String key, int def) {
    String val = Misc.getEnvStr(key, null);
    return val == null ? def : Misc.forceInt0(val);
  }

  /**
   * 获得并设置(如果未设置)环境变量的值
   */
  public static final String getSetEnvStr(String key, String def) {
    String val = Misc.getEnvStr(key, def);
    Misc.setEnv(key, val);
    return val;
  }

  /**
   * 获得并设置(如果未设置)环境变量的值
   */
  public static final int getSetEnvInt(String key, int def) {
    Integer val = Misc.getEnvInt(key, def);
    Misc.setEnv(key, val);
    return val;
  }

  /**
   * 将系统环境变量顺序列出.
   */
  public static final ArrayList<Entry<Object, Object>> getEnvs() {
    ArrayList<Entry<Object, Object>> arr = new ArrayList<Entry<Object, Object>>();
    System.getProperties().entrySet().forEach(o -> arr.add(o));
    arr.sort(new Comparator<Entry<Object, Object>>() {
      public int compare(Entry<Object, Object> o1, Entry<Object, Object> o2) {
        return o1.getKey().toString().compareTo(o2.getKey().toString());
      }
    });
    return arr;
  }

  /**
   * 将系统环境变量顺序列出.
   */
  public static final String sysEnvs() {
    ArrayList<Entry<Object, Object>> arr = Misc.getEnvs();
    StringBuilder strb = new StringBuilder();
    arr.forEach(v -> strb.append(v.getKey()).append("=").append(v.getValue()).append("\n"));
    return strb.toString();
  }

  /**
   * 打印系统环境变量.
   */
  public static final StringBuilder printEnvs() {
    ArrayList<Entry<Object, Object>> envs = Misc.getEnvs();
    StringBuilder strb = new StringBuilder();
    envs.forEach((e) -> strb.append(e.getKey()).append("=").append(e.getValue()).append(Misc.LINE));
    return strb;
  }

  /**
   * 是不是windows平台.
   */
  public static final boolean isWindowsPlatform() {
    return File.separatorChar == '\\';
  }

  /**
   * 环境变量, 当前路径.
   */
  public static final String getPwd() {
    return System.getProperty("user.dir");
  }

  /**
   * 延迟(5秒后)退出程序.
   */
  public static final void lazySystemExit() {
    Misc.sleep(5000);
    System.exit(1);
  }

  /**
   * 获得WEB工程工作目录.
   */
  public static final String getPwd(Class<?> webcls /* 这是一个位于WEB-INF/classes中的类. */) {
    try {
      String pkg = webcls.getPackage().getName().replace('.', '/');
      Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(pkg);
      if (!dirs.hasMoreElements()) {
        logger.error("can not got web-service pwd.");
        Misc.lazySystemExit();
        return null;
      }
      String path = URLDecoder.decode(dirs.nextElement().getFile(), "UTF-8");
      return path.substring(0, path.indexOf("classes/" + pkg));
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("can not got web-service pwd.\n{}", Misc.trace(e));
      }
      Misc.lazySystemExit();
      return null;
    }
  }

  /**
   * 执行Consumer并将异常化解在内部.
   */
  public static final <T> boolean exeConsumer(Consumer<T> c, T t) {
    try {
      c.accept(t);
      return true;
    } catch (Exception e) {
      if (logger.isWarnEnabled()) {
        logger.warn("{}", Misc.trace(new Throwable()));
      }
      if (logger.isWarnEnabled()) {
        logger.warn("t: {}, e: {}", t, Misc.trace(e));
      }
      return false;
    }
  }

  /**
   * 执行Function并将异常化解在内部.
   */
  public static final <T, R> R exeFunction(Function<T, R> f, T t) {
    try {
      return f.apply(t);
    } catch (Exception e) {
      if (logger.isWarnEnabled()) {
        logger.warn("{}", Misc.trace(e));
      }
      return null;
    }
  }


  /**
   * 执行BiConsumer并将异常化解在内部.
   */
  public static final <K, V> boolean exeBiConsumer(BiConsumer<? super K, ? super V> c, K k, V v) {
    try {
      c.accept(k, v);
      return true;
    } catch (Exception e) {
      if (logger.isWarnEnabled()) {
        logger.warn("{}", Misc.trace(e));
      }
      logger.warn("k: {}, v: {}, e: {}", k, v, Misc.trace(e));
      return false;
    }
  }

  /**
   * do nothing.
   */
  public static final void donothing() {

  }

  public static final String trace(Throwable e) {
    StringBuilder strb = new StringBuilder(0x200);
    StackTraceElement ste[] = e.getStackTrace();
    strb.append(e).append(Misc.LINE);
    for (int i = 0; i < ste.length; i++) {
      strb.append(ste[i].toString()).append(Misc.LINE);
    }
    return strb.toString();
  }

  /**
   * 对象转字节数组
   *
   * @param obj 对象
   * @return 字节数组
   */
  public static byte[] object2Bytes(Object obj) {
    byte[] bytes = null;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = null;
    try {
      objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(obj);
      objectOutputStream.flush();
      bytes = byteArrayOutputStream.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (objectOutputStream != null) {
        try {
          objectOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          try {
            byteArrayOutputStream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return bytes;
  }

  /**
   * 字节数组转对象
   *
   * @param bytes 字节数组
   * @return 对象
   */
  public static <T> Optional<T> bytes2Object(byte[] bytes) {
    T object = null;
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    ObjectInputStream objectInputStream = null;
    try {
      objectInputStream = new ObjectInputStream(byteArrayInputStream);
      object = (T) objectInputStream.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (objectInputStream != null) {
        try {
          objectInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return Optional.ofNullable(object);
  }
}
