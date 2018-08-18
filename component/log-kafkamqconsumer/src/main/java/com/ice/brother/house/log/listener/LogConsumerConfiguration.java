package com.ice.brother.house.log.listener;

import com.ice.brother.house.es.client.ESClient;
import com.ice.brother.house.log.configuration.KafkaMqConfiguration;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/7/12 09:35
 */
@Component
public class LogConsumerConfiguration implements ConsumerListener {

  private static final Logger logger = LoggerFactory.getLogger(LogConsumerConfiguration.class);

  @Value("${kafka.log.group}")
  private String logGroup;

  @Value("${kafka.log.topic}")
  private String logTopic;

  @Autowired
  private KafkaMqConfiguration kafkaMqConfiguration;

  @Autowired
  private ESClient esClient;

  @PostConstruct
  public void init() {
    logger.info("kafka消费");
    this.consumer();
  }

  @Override
  public void consumer() {
    logger.info("web operate log consumer start");
    kafkaMqConfiguration.kafkaConsumer(logGroup, logTopic);
    new Thread(() -> kafkaMqConfiguration.start((ts, msg) -> {
      logger.info("消息发送");
    })).start();
  }

}
