package com.ice.brother.house.bos.web.shiro;

import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author:ice
 * @Date: 2018/8/3 19:27
 */
@Service
public class ShiroRoleFilter extends AuthorizationFilter {

  private static final Logger logger = LoggerFactory.getLogger(ShiroRoleFilter.class);

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
      Object mappedValue) throws Exception {
    Subject subject = getSubject(request, response);
    String[] rolesArray = (String[]) mappedValue;

    if (rolesArray == null || rolesArray.length == 0) {
      // no roles specified, so nothing to check - allow access.
      return true;
    }
    //校验角色菜单权限
    Set<String> roles = CollectionUtils.asSet(rolesArray);
    for (String role : roles) {
      if (subject.hasRole(role)) {
        return true;
      }
    }
    return false;
  }
}
