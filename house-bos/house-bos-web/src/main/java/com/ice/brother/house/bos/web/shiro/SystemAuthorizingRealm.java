package com.ice.brother.house.bos.web.shiro;

import com.ice.brother.house.bos.provider.entities.SysUser;
import com.ice.brother.house.bos.provider.service.SysUserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/7/31 19:26
 */
@Component(value = "authRealm")
public class SystemAuthorizingRealm extends AuthorizingRealm {

  public static final Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);

  @Autowired
  private SysUserService sysUserService;

  @Autowired
  private CredentialsMatcher matcher;

  @PostConstruct
  private void setProperties() {
    setCredentialsMatcher(matcher);
    setCachingEnabled(false);
    setAuthorizationCachingEnabled(false);
    setAuthenticationCachingEnabled(false);
  }

  /**
   * 认证信息
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    logger.info("doGetAuthenticationInfo 认证");
    ShiroUsernamePasswordToken token = (ShiroUsernamePasswordToken) authenticationToken;
    SysUser params = new SysUser();
    params.userName = token.getUsername();
    SysUser user = null;
    try {
      user = sysUserService.selectSysUserByName(params);
    } catch (Exception e) {
      logger.warn("find user error");
      return null;
    }
    if (user != null) {
      //获取用户角色列表.
      SysUser userRoles = sysUserService.selectUserRole(user.userId);
      user.roleInfos.addAll(userRoles.roleInfos);

      AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.pwd,
          user.userName);
      return authcInfo;
    } else {
      return null;
    }
  }

  /**
   * 授权
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    logger.info("doGetAuthorizationInfo 授权");
    SysUser user = (SysUser) super.getAvailablePrincipal(principalCollection);
    List<String> roleList = new ArrayList<>();
    List<String> permissions = new ArrayList<>();
    //
    permissions.add("create");
    //
    if (user.roleInfos.size() > 0) {
      user.roleInfos.forEach(role -> roleList.add(role.roleName));
    }

    SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
    simpleAuthorInfo.addRoles(roleList);
    simpleAuthorInfo.addStringPermissions(permissions);
    return null;
  }


}
