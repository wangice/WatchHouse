package com.ice.brother.house.bos.provider.mapper;

import com.ice.brother.house.bos.provider.entities.SysUser;

/**
 * @author:ice
 * @Date: 2018/7/31 19:50
 */
public interface SysUserMapper {

  /**
   * 通过用户名查询用户
   */
  SysUser selectSysUserByName(SysUser sysUser);

  /**
   * 获取用户角色
   */
  SysUser selectUserRole(int userId);

}
