package com.ice.brother.house.app.web.controller;

import com.ice.brother.house.app.web.core.Rsp.RspErr;
import com.ice.brother.house.app.web.rsp.RspLogin;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ice
 * @Date: 2018/6/16 11:25
 */
@RestController
public class HelloController extends BaseController {

  @GetMapping("hello/{name}")
  public Object hello(@PathVariable String name) throws IOException {
    RspLogin rspLogin = new RspLogin();
    rspLogin.setUserName(name);
    return transEnd(RspErr.ERR_NONE, rspLogin);
  }

}
