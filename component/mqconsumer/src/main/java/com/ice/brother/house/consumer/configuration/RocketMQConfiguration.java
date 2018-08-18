package com.ice.brother.house.consumer.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author:ice
 * @Date: 2018/8/9 18:12
 */
public class RocketMQConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(RocketMQConfiguration.class);
  @Value("${rocketmq.namesrvAddr}")
  private String nameSrvAddr;
  @Value("${rocketmq.producerGroup}")
  private String producerGroup;
  @Value("${rocketmq.consumerGroup}")
  private String consumerGroup;


}
