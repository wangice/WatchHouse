package com.ice.brother.house.mqconsumer;

import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author:ice
 * @Date: 2018/7/11 16:42
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
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
