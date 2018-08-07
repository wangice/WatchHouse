package com.ice.brother.house.bos.web.configuration;

import java.util.Locale;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleMessageSourceService {

  @Resource
  private MessageSource messageSource;

  public String getMessage(String keyword, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(keyword, args, locale);
  }
}
