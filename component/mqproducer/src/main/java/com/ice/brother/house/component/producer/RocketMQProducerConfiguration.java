package com.ice.brother.house.component.producer;

import javax.annotation.PreDestroy;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author:ice
 * @Date: 2018/8/9 17:35
 */
@Configuration
@PropertySource(value = "classpath:/rocketmq-${spring.profiles.active}.properties")
public class RocketMQProducerConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(RocketMQProducerConfiguration.class);
  @Value("${rocketmq.namesrvAddr}")
  private String nameSrvAddr;
  @Value("${rocketmq.producerGroup}")
  private String producerGroup;
  @Value("${rocketmq.consumerGroup}")
  private String consumerGroup;
  private DefaultMQProducer defaultMQProducer;

  @Bean
  public DefaultMQProducer defaultMQProducer() {
    logger.info("RocketMQ defaultMQProducer Starting ....");
    logger.info("*  NameServerAddress is {} , producerGroup is {}", nameSrvAddr, producerGroup);
    defaultMQProducer = new DefaultMQProducer(producerGroup);
    defaultMQProducer.setNamesrvAddr(nameSrvAddr);
    defaultMQProducer.setVipChannelEnabled(false);
    try {
      defaultMQProducer.start();
    } catch (MQClientException e) {
      e.printStackTrace();
      logger.warn("RocketMQ defaultMQProducer Start  Exception ! e={}", e);
    }
    logger.info("RocketMQ defaultMQProducer Start Success!");
    return defaultMQProducer;
  }

  @PreDestroy
  public void destroy() {
    if (defaultMQProducer != null) {
      defaultMQProducer.shutdown();
    }
  }
}
