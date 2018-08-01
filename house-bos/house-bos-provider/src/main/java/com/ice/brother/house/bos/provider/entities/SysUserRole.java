package com.ice.brother.house.bos.provider.entities;

import java.io.Serializable;

/**
 * 系统用户与角色对应关系
 *
 * @author:ice
 * @Date: 2018/7/31 17:30
 */
public class SysUserRole implements Serializable {

  private static final long serialVersionUID = -1104211536090386265L;

  public int id;//主键
  public int userId;//用户ID
  public int roleId;//角色ID

}
