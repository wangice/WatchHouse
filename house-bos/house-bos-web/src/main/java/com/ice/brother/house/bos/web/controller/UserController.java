package com.ice.brother.house.bos.web.controller;

import com.ice.brother.house.bos.provider.service.SysUserService;
import com.ice.brother.house.bos.web.configuration.I18nConstant;
import com.ice.brother.house.bos.web.configuration.LocaleMessageSourceService;
import com.ice.brother.house.bos.web.rsp.Rsp;
import com.ice.brother.house.bos.web.rsp.Rsp.RspErr;
import com.ice.brother.house.bos.web.rsp.RspQueryProjectVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ice
 * @Date: 2018/8/2 19:18
 */
@RestController
public class UserController extends BaseController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private SysUserService sysUserService;

  @Autowired
  private LocaleMessageSourceService localeMessageSourceService;

  @GetMapping("/queryUserList")
  public Rsp queryUserList() throws Exception {
    logger.info("输出");
    RspQueryProjectVersion rsp = new RspQueryProjectVersion();
    rsp.projectVersion = localeMessageSourceService.getMessage(I18nConstant.MESSAGE, null);
    return transEnd(RspErr.ERR_NONE, rsp);
  }

}
