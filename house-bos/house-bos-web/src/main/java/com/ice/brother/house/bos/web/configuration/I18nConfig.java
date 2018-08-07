package com.ice.brother.house.bos.web.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * @author:ice
 * @Date: 2018/8/7 10:15
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {

  /**
   * 配置以Session为最高优先级找寻方式
   */
  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setCookieName("locale");
    //单位 秒,从创建时间开始到结束
    cookieLocaleResolver.setCookieMaxAge(7 * 24 * 60 * 60);

    return cookieLocaleResolver;
  }

  /**
   * i18n国际化配置 beanName必须是messageSource
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    //配置目录
    messageSource.setBasename("classpath:i18n/messages");
    //检查国际化文件是否刷新的频率，单位毫秒，这个时间只是监控的频率，最终还要看文件修改的时间和上一次文件修改的时间是否一致
    messageSource.setCacheMillis(3 * 60 * 1000);
    //配置编码
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public RequestLocaleChangeInterceptor localeChangeInterceptor() {
    RequestLocaleChangeInterceptor localeChangeInterceptor = new RequestLocaleChangeInterceptor();
    return localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}
