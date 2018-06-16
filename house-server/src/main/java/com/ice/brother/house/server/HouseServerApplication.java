package com.ice.brother.house.server;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author:ice
 * @Date: 2018/6/15 11:36
 */
@EnableEurekaServer
@SpringBootApplication(exclude = {ConfigurationPropertiesRebinderAutoConfiguration.class})
public class HouseServerApplication {

  public static final AtomicBoolean RUN = new AtomicBoolean(true);

  public static void main(String[] args) {
    SpringApplication.run(HouseServerApplication.class, args);
    synchronized (HouseServerApplication.class) {
      while (RUN.get()) {
        try {
          HouseServerApplication.class.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
