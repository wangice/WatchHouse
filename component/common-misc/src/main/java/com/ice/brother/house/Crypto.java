package com.ice.brother.house;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ice
 * @create on:2018-04-13 01:46
 */
public class Crypto {

  private static final Logger logger = LoggerFactory.getLogger(Crypto.class);

  /**
   * BASE64-ENCODE.
   */
  public static final String base64enc(byte by[]) {
    try {
      return Base64.getEncoder().encodeToString(by);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * BASE64-DECODE.
   */
  public static final byte[] base64dec(String str) {
    try {
      return Base64.getDecoder().decode(str);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * RC4加密.
   */
  public static final byte[] rc4enc(byte key[], byte[] org) {
    return Crypto.rc4enc(key, org, 0, org.length);
  }

  /**
   * RC4加密.
   */
  public static final byte[] rc4enc(byte key[], byte[] org, int ofst, int len) {
    try {
      Key k = new SecretKeySpec(key, "RC4");
      Cipher cip = Cipher.getInstance("RC4");
      cip.init(Cipher.ENCRYPT_MODE, k);
      return cip.doFinal(org, ofst, len);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * RC4解密.
   */
  public static final byte[] rc4dec(byte key[], byte[] crypto) {
    return Crypto.rc4dec(key, crypto, 0, crypto.length);
  }

  /**
   * RC4解密.
   */
  public static final byte[] rc4dec(byte key[], byte[] crypto, int ofst, int len) {
    try {
      Key k = new SecretKeySpec(key, "RC4");
      Cipher cip = Cipher.getInstance("RC4");
      cip.init(Cipher.DECRYPT_MODE, k);
      return cip.doFinal(crypto, ofst, len);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * RSA私钥加密.
   */
  public static final byte[] rsaPrivateEnc(byte priKey[], byte[] org) {
    try {
      PKCS8EncodedKeySpec specx = new PKCS8EncodedKeySpec(priKey);
      PrivateKey pri = KeyFactory.getInstance("RSA").generatePrivate(specx);
      //
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, pri);
      //
      if (org.length < 117) {
        return cipher.doFinal(org);
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int c = org.length / 117;
      int m = org.length % 117;
      for (int i = 0; i < c; ++i) {
        out.write(cipher.doFinal(org, i * 117, 117));
      }
      if (m != 0) {
        out.write(cipher.doFinal(org, c * 117, m));
      }
      return out.toByteArray();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * RSA私钥解密.
   */
  public static final byte[] rsaPrivateDec(byte priKey[], byte[] org) {
    try {
      PKCS8EncodedKeySpec specx = new PKCS8EncodedKeySpec(priKey);
      PrivateKey pri = KeyFactory.getInstance("RSA").generatePrivate(specx);
      //
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, pri);
      //
      if (org.length < 128) {
        return cipher.doFinal(org);
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int c = org.length / 128;
      int m = org.length % 128;
      for (int i = 0; i < c; ++i) {
        out.write(cipher.doFinal(org, i * 128, 128));
      }
      if (m != 0) {
        out.write(cipher.doFinal(org, c * 128, m));
      }
      return out.toByteArray();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * RSA公钥加密.
   */
  public static final byte[] rsaPublicEnc(byte pubKey[], byte[] org) {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKey);
      PublicKey pub = KeyFactory.getInstance("RSA").generatePublic(spec);
      //
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, pub);
      //
      if (org.length < 117) {
        return cipher.doFinal(org);
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int c = org.length / 117;
      int m = org.length % 117;
      for (int i = 0; i < c; ++i) {
        out.write(cipher.doFinal(org, i * 117, 117));
      }
      if (m != 0) {
        out.write(cipher.doFinal(org, c * 117, m));
      }
      return out.toByteArray();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * RSA公钥解密.
   */
  public static final byte[] rsaPublicDec(byte pubKey[], byte[] org) {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKey);
      PublicKey pub = KeyFactory.getInstance("RSA").generatePublic(spec);
      //
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, pub);
      //
      if (org.length < 128) {
        return cipher.doFinal(org);
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int c = org.length / 128;
      int m = org.length % 128;
      for (int i = 0; i < c; ++i) {
        out.write(cipher.doFinal(org, i * 128, 128));
      }
      if (m != 0) {
        out.write(cipher.doFinal(org, c * 128, m));
      }
      return out.toByteArray();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * MD5摘要算法.
   */
  public static final byte[] md5(byte[] by) {
    try {
      return MessageDigest.getInstance("MD5").digest(by);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * MD5摘要算法, 以大写16进制字符串方式返回.
   */
  public static final String md5StrUpperCase(byte by[]) {
    return Net.byte2HexStr(Crypto.md5(by));
  }

  /**
   * MD5摘要算法, 以小写16进制字符串方式返回.
   */
  public static final String md5StrLowerCase(byte by[]) {
    return Net.byte2HexStr(Crypto.md5(by)).toLowerCase();
  }

  /**
   * SHA-1摘要算法.
   */
  public static final byte[] sha1(byte[] by) {
    try {
      return MessageDigest.getInstance("SHA-1").digest(by);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * SHA-1摘要算法, 以大写16进制字符串方式返回.
   */
  public static final String sha1StrUpperCase(byte by[]) {
    return Net.byte2HexStr(Crypto.sha1(by));
  }

  /**
   * SHA-1摘要算法, 以小写16进制字符串方式返回.
   */
  public static final String sha1StrLowerCase(byte by[]) {
    return Net.byte2HexStr(Crypto.sha1(by)).toLowerCase();
  }

  /**
   * SHA-256摘要算法.
   */
  public static final byte[] sha256(byte[] by) {
    try {
      return MessageDigest.getInstance("SHA-256").digest(by);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * SHA-256摘要算法, 以大写16进制字符串方式返回.
   */
  public static final String sha256StrUpperCase(byte by[]) {
    return Net.byte2HexStr(Crypto.sha256(by));
  }

  /**
   * SHA-256摘要算法, 以小写16进制字符串方式返回.
   */
  public static final String sha256StrLowerCase(byte by[]) {
    return Net.byte2HexStr(Crypto.sha256(by)).toLowerCase();
  }

  /**
   * SHA-512摘要算法.
   */
  public static final byte[] sha512(byte[] by) {
    try {
      return MessageDigest.getInstance("SHA-512").digest(by);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("{}", Misc.trace(e));
      }
      return null;
    }
  }

  /**
   * SHA-512摘要算法, 以大写16进制字符串方式返回.
   */
  public static final String sha512StrUpperCase(byte by[]) {
    return Net.byte2HexStr(Crypto.sha512(by));
  }

  /**
   * SHA-512摘要算法, 以小写16进制字符串方式返回.
   */
  public static final String sha512StrLowerCase(byte by[]) {
    return Net.byte2HexStr(Crypto.sha512(by)).toLowerCase();
  }

  /**
   * 生成一个uuid(无"-"符号).
   */
  public static final String uuid() {
    return new UUID(Misc.randLang(), Misc.randLang()).toString().replaceAll("-", "");
  }

  /**
   * CRC32散列算法.
   */
  public static final long crc32(String str) {
    CRC32 crc = new CRC32();
    crc.update(str.getBytes());
    return crc.getValue();
  }
}
