package com.ice.brother.house.mqconsumer;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author:ice
 * @Date: 2018/7/11 16:42
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@PropertySource(value = {"elasticsearch-${spring.profiles.active}.properties"})
@ImportResource(value = {"classpath:elasticsearch-config.xml"})
public class KafkaMqComsumerApplication {

  public static final AtomicBoolean RUN = new AtomicBoolean(true);

  public static void main(String[] args) {
    SpringApplication.run(KafkaMqComsumerApplication.class, args);
    synchronized (KafkaMqComsumerApplication.class) {
      while (RUN.get()) {
        try {
          KafkaMqComsumerApplication.class.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
