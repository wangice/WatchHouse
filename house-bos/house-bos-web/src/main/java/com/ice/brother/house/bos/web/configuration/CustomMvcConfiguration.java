package com.ice.brother.house.bos.web.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * WebMvcConfigurationSupport 会让自动配置失效,需要配置才能让static/*下的静态资源显示
 *
 * WebMvcConfigurer则不会
 *
 * @author:ice
 * @Date: 2018/8/2 11:47
 */
@Configuration
public class CustomMvcConfiguration extends WebMvcConfigurationSupport {

  @Bean
  public HttpMessageConverter<String> responseBodyConverter() {
    StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    return converter;
  }

  @Bean
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public MappingJackson2HttpMessageConverter messageConverter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(getObjectMapper());
    return converter;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(converters);
    //解决中文乱码
    converters.add(responseBodyConverter());
    //解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错
    converters.add(messageConverter());
  }

}
