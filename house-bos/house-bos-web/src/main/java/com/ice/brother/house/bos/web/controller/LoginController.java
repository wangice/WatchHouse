package com.ice.brother.house.bos.web.controller;

import com.ice.brother.house.bos.web.rsp.Rsp;
import com.ice.brother.house.bos.web.rsp.Rsp.RspErr;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ice
 * @Date: 2018/8/1 16:32
 */
@RestController
public class LoginController extends BaseController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  /**
   * 用户登录
   */
  @GetMapping("/login")
  public Rsp login(HttpServletRequest request) throws Exception{
    logger.info("login 登录信息");
    String userName = request.getParameter("userName");
    String pwd = request.getParameter("password");
    if (userName == null || pwd == null) {
      return transEnd(RspErr.ERR_MISSING_PARAMS);
    }
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
    try {
      subject.login(token);
    } catch (AuthenticationException e) {
      return transEnd(RspErr.ERR_LOGIN_AUTH_ERROR);
    }
    return transEnd(RspErr.ERR_NONE);
  }
}
