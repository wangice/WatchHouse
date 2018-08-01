package com.ice.brother.house.bos.provider.entities;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 *
 * @author:ice
 * @Date: 2018/7/31 17:25
 */
public class RoleInfo implements Serializable {

  private static final long serialVersionUID = -8654945139785322143L;

  public int roleId;//角色ID.
  public String roleName;//角色名称
  public List<SysUser> sysUsers;//角色对应的用户列表
  public String remark;//描述


}
