package com.ice.brother.house.log.configuration;

import com.google.protobuf.Message;
import com.ice.brother.house.Misc;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.function.BiConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/7/11 18:20
 */
@Component
public class KafkaMqConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(KafkaMqConfiguration.class);

  @Value("${bootstrap.servers}")
  private String srv;
  @Value("${enable.auto.commit}")
  private String commit;
  @Value("${auto.commit.interval.ms}")
  private String commitInterval;
  @Value("${key.deserializer}")
  private String keyDeserializer;
  @Value("${value.deserializer}")
  private String valueDeserializer;

  private KafkaConsumer<String, Bytes> cons;

  /**
   * 消息存根.
   */
  private HashMap<String /* 消息类名. */, Method /* 消息构造函数. */> msgMths = new HashMap<>();

  public KafkaConsumer<String, Bytes> kafkaConsumer(String group/* 消费组. */,
      String topic/* 生成主题. */) {
    Properties pro = new Properties();
    pro.put("bootstrap.servers", srv);
    pro.put("group.id", group);
    pro.put("enable.auto.commit", commit);
    pro.put("auto.commit.interval.ms", commitInterval);
    pro.put("key.deserializer", keyDeserializer);
    pro.put("value.deserializer", valueDeserializer);
    this.cons = new KafkaConsumer<>(pro);
    cons.subscribe(Arrays.asList(topic));
    return cons;
  }

  /**
   * 拉取消息进行消费.
   */
  public void start(BiConsumer<Long, Message> c) {
    while (true) {
      try {
        ConsumerRecords<String, Bytes> records = this.cons.poll(100);
        if (records == null) {
          logger.trace("no message in topic");
          Misc.sleep(50);
          continue;
        }
        records.forEach(record -> {
          logger.info("消费消息：key:{},value:{}", record.key(), record.value());
        });
      } catch (Exception e) {
        logger.error("%s", Misc.trace(e));
      }
      Misc.sleep(50);
    }
  }

}
