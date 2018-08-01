package com.ice.brother.house.bos.provider.service;

import com.ice.brother.house.bos.provider.entities.SysUser;
import com.ice.brother.house.bos.provider.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:ice
 * @Date: 2018/7/31 19:53
 */
@Service
public class SysUserServiceImpl implements SysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Override
  public SysUser selectSysUserByName(SysUser sysUser) {
    return sysUserMapper.selectSysUserByName(sysUser);
  }

  @Override
  public SysUser selectUserRole(int userId) {
    return sysUserMapper.selectUserRole(userId);
  }
}
