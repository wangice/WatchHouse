package com.ice.brother.house.bos.web.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

public class RequestLocaleChangeInterceptor extends LocaleChangeInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    String lang = request.getHeader("lang");

    if (!StringUtils.isEmpty(lang)) {
      localeResolver.setLocale(request, response, parseLocaleValue(lang.toLowerCase()));
    } else {
      //先从cookie中获取，cookie中没有再从header中获取 language:en,zh_CN
      localeResolver.setLocale(request, response, localeResolver.resolveLocale(request));
    }
    return true;
  }
}
