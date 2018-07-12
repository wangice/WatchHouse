package com.ice.brother.house.mqconsumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/7/12 09:35
 */
@Component
public class LogComsumerConfiguration implements ConsumerListener {

  @Autowired
  private KafkaMqConfiguration kafkaMqConfiguration;

  @Override
  public void consumer() {
    kafkaMqConfiguration.kafkaConsumer("", "");
    new Thread(() -> kafkaMqConfiguration.start((ts, msg) -> {

      //消费消息.

    })).start();
  }

}
