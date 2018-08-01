package com.ice.brother.house.bos.provider.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author:ice
 * @Date: 2018/7/31 17:19
 */
public class SysUser implements Serializable {

  private static final long serialVersionUID = 759542622994164450L;

  public int userId;//用户ID
  public String userName;//用户名称
  public String pwd;//密码
  public String creator;//创建者
  public Date createTime;//创建时间
  public String updator;//更新者
  public Date updateTime;//更新时间

  public int roleId;//角色id
  public String roleName;//角色名称
  public String remark;//描述
  public List<RoleInfo> roleInfos = new ArrayList<>();//用户对应的角色ID
}
