package com.ice.brother.house.app.provider.service;

import com.ice.brother.house.app.provider.entities.User;

/**
 * @author:ice
 * @Date: 2018/6/16 14:19
 */
public interface UserService {

  /**
   * 通过email查找登录用户
   */
  User userLogin(String email);

}
