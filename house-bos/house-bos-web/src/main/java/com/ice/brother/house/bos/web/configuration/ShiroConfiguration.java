package com.ice.brother.house.bos.web.configuration;

import com.ice.brother.house.bos.web.shiro.MySessionListener;
import com.ice.brother.house.bos.web.shiro.ShiroUsernamePasswordToken;
import com.ice.brother.house.bos.web.shiro.SystemAuthorizingRealm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:ice
 * @Date: 2018/7/31 19:13
 */
@Configuration
public class ShiroConfiguration {

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
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    // 配置不会被拦截的链接 顺序判断
    filterChainDefinitionMap.put("/static/**", "anon");
    //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
    filterChainDefinitionMap.put("/logout", "logout");
    //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
    //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
    filterChainDefinitionMap.put("/**", "authc");
    // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    //未授权界面;
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }


}
