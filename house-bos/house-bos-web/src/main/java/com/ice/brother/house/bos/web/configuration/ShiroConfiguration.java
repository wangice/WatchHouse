package com.ice.brother.house.bos.web.configuration;

import com.ice.brother.house.bos.web.shiro.MySessionListener;
import com.ice.brother.house.bos.web.shiro.ShiroRoleFilter;
import com.ice.brother.house.bos.web.shiro.ShiroUsernamePasswordToken;
import com.ice.brother.house.bos.web.shiro.SystemAuthorizingRealm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author:ice
 * @Date: 2018/7/31 19:13
 */
@Configuration
public class ShiroConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

  @Value("${auth.adminUrl}")
  private String adminUrl;
  @Value("${auth.anonUrl}")
  private String anonUrl;

  @Bean
  public CredentialsMatcher credentialsMatcher() {
    return (authenticationToken, authenticationInfo) -> {
      ShiroUsernamePasswordToken token = (ShiroUsernamePasswordToken) authenticationToken;
      String upwd = new String(token.getPassword());
      //获取数据库中密码
      String pwd = (String) authenticationInfo.getCredentials();
      //进行密码的比对
      return StringUtils.equals(upwd, pwd);
    };
  }

  @Bean(name = "sessionManager")
  public SessionManager sessionManager() {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    Collection<SessionListener> listeners = new ArrayList<SessionListener>();
    listeners.add(new MySessionListener());
    sessionManager.setSessionListeners(listeners);
    sessionManager.setSessionIdCookieEnabled(true);
    sessionManager.setSessionIdUrlRewritingEnabled(false);
    return sessionManager;
  }

  /**
   * SecurityManager，权限管理，这个类组合了登陆，登出，权限，是个比较重要的类。
   */
  @Bean(name = "securityManager")
  public SecurityManager securityManager(SystemAuthorizingRealm authRealm,
      SessionManager sessionManager) {
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    manager.setRealm(authRealm);
    manager.setSessionManager(sessionManager);
    return manager;
  }


  @Bean
  public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    //拦截器.
    shiroFilterFactoryBean.setLoginUrl("/login");// 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");//未授权界面;
    Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
    filters.put("authc", new ShiroRoleFilter());//拦截事件
    shiroFilterFactoryBean.setFilters(filters);
    //
    Map<String, String> chains = new LinkedHashMap<String, String>();
    if (StringUtils.isNotBlank(adminUrl)) {
      String[] adminUrlArr = adminUrl.split(",");
      for (String url : adminUrlArr) {
        chains.put(url, "authc");
      }
    }
    if (StringUtils.isNotBlank(anonUrl)) {
      String[] anonUrlArr = anonUrl.split(",");
      for (String url : anonUrlArr) {
        chains.put(url, "anon");
      }
    }
    shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
    return shiroFilterFactoryBean;
  }

  @Bean
  public Filter encodingFilter() {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    return filter;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager manager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(manager);
    return advisor;
  }
}
