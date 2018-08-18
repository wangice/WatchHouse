package com.ice.brother.house.advert.controller;

import com.ice.brother.house.advert.rsp.Rsp;
import com.ice.brother.house.advert.rsp.Rsp.RspErr;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:ice
 * @Date: 2018/8/9 11:55
 */
@RestController
public class AdvertController extends BaseController {

  private static final Logger logger = LogManager.getLogger(AdvertController.class);

  @ResponseBody
  @GetMapping("/query/advert")
  public Rsp queryAdvert() {
    logger.info("hello query advert,{}","dd");
    return transEnd(RspErr.ERR_NONE);
  }

}
