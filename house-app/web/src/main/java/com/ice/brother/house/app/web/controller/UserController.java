package com.ice.brother.house.app.web.controller;

import com.ice.brother.house.app.provider.entities.User;
import com.ice.brother.house.app.provider.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ice
 * @Date: 2018/6/16 11:45
 */
@RestController
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/login")
  public String login(HttpServletRequest request) {
    String email = request.getParameter("email");
    if (email == null) {
      return "email 不正确";
    }
    User user = userService.userLogin(email);
    if (user == null) {
      return "email 错误";
    }
    return "登录成功";
  }
}
