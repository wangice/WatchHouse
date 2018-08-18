package com.ice.brother.house.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * RocketMQ消息消费
 *
 * @author Charles
 * @create 2017-06-20 19:20
 **/
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class RocketMQConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketMQConsumerApplication.class, args);
  }
}
