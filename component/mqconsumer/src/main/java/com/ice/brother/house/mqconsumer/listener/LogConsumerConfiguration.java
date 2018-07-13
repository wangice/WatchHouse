package com.ice.brother.house.mqconsumer.listener;

import com.ice.brother.house.Misc;
import com.ice.brother.house.common.proto.OperateLog.WebOperateLog;
import com.ice.brother.house.es.client.ESClient;
import java.io.IOException;
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
      WebOperateLog webOperateLog = (WebOperateLog) msg;
      logger.info("web operate log : {}", webOperateLog.toString());
      try {
        logger.info("es push");
        esClient.postRquestAsync("weboperatelog", webOperateLog.toString());
      } catch (IOException e) {
        logger.info("es post push error:{}", Misc.trace(e));
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    })).start();
  }

}
