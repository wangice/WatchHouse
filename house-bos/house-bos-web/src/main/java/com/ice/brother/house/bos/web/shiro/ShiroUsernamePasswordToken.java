package com.ice.brother.house.bos.web.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author:ice
 * @Date: 2018/7/31 19:16
 */
public class ShiroUsernamePasswordToken extends UsernamePasswordToken {

  private static final long serialVersionUID = 905337924507534264L;

  public String token;

  public ShiroUsernamePasswordToken(String username, char[] password, String token) {
    super(username, password);
    this.token = token;
  }
}
