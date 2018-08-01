package com.ice.brother.house.bos.provider.service;

import com.ice.brother.house.bos.provider.entities.SysUser;

/**
 * @author:ice
 * @Date: 2018/7/31 19:52
 */
public interface SysUserService {

  /**
   * 通过用户名查询用户
   */
  SysUser selectSysUserByName(SysUser sysUser);

  /**
   * 通过用户ID获取用户角色
   */
  SysUser selectUserRole(int userId);
}
