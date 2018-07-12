package com.ice.brother.house.app.web;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author:ice
 * @Date: 2018/6/16 11:08
 */
@EnableDiscoveryClient
@SpringBootApplication
@PropertySource(value = {"provider-${spring.profiles.active}.properties"})
@ImportResource(value = {"classpath:provider.xml", "classpath:mqproducer-config.xml"})
public class AppWebApplication {

  public static final AtomicBoolean RUN = new AtomicBoolean(true);

  public static void main(String[] args) {
    SpringApplication.run(AppWebApplication.class, args);
    synchronized (AppWebApplication.class) {
      while (RUN.get()) {
        try {
          AppWebApplication.class.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
