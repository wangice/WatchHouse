package com.ice.brother.house.advert.controller;

import com.ice.brother.house.advert.rsp.Rsp;
import com.ice.brother.house.advert.rsp.Rsp.RspErr;

/**
 * @author:ice
 * @Date: 2018/6/16 11:45
 */
public class BaseController {

  /**
   * 返回参数
   */
  public Rsp transEnd(RspErr rspErr) {
    Rsp rsp = new Rsp();
    rsp.setErr(rspErr.getErrCode());
    rsp.setDesc(rspErr.getErrName());
    return rsp;
  }

  /**
   * 返回参数,有正文
   */
  public Rsp transEnd(RspErr rspErr, Object obj) {
    Rsp rsp = new Rsp();
    rsp.setErr(rspErr.getErrCode());
    rsp.setDesc(rspErr.getErrName());
    rsp.setDat(obj);
    return rsp;
  }
}
