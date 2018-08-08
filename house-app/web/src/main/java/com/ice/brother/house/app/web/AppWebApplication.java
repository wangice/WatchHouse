package com.ice.brother.house.app.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author:ice
 * @Date: 2018/6/16 11:08
 */
@ServletComponentScan
@EnableDiscoveryClient
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class, CommonsClientAutoConfiguration.class, HystrixAutoConfiguration.class})
@PropertySource(value = {"provider-${spring.profiles.active}.properties"})
@ImportResource(value = {"classpath:provider.xml", "classpath:mqproducer-config.xml","classpath:task-config.xml"})
public class AppWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppWebApplication.class, args);
  }
}
