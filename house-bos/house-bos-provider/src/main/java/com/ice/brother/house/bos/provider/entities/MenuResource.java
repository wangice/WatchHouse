package com.ice.brother.house.bos.provider.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资源
 *
 * @author:ice
 * @Date: 2018/7/31 18:01
 */
public class MenuResource implements Serializable {

  private static final long serialVersionUID = 9185880868097864366L;

  public int id;//主键
  public int parentMenuId;//父一级菜单
  public String menuName;//资源名称
  public String parentMenuName;//父级菜单名称
  public String menuUrl;//子菜单路径
  public List<MenuResource> childMenu;//子菜单
  public String creator;//创建者
  public Date createTime;//创建时间
  public String roleId;//用户角色ID

}
