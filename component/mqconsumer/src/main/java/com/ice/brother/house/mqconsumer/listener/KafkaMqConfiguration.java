package com.ice.brother.house.mqconsumer.listener;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Message;
import com.ice.brother.house.Misc;
import com.ice.brother.house.Net;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/7/11 18:20
 */
@Component
@PropertySource(value = "classpath:kafka_${spring.profiles.active}.properties")
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
          try {
            Method mth = this.msgMths.get(record.key());
            if (mth == null) {
              Class<?> cls = Class.forName(record.key());
              if (cls == null) {
                logger.error("can not found class for name, msg: {}", record.key());
                return;
              }
              mth = cls.getMethod("newBuilder");
              if (mth == null) {
                logger.error("it`s a bug, msg: {}", record.key());
                return;
              }
              this.msgMths.put(record.key(), mth);
            }
            byte by[] = record.value().get();
            Message msg = ((AbstractMessage.Builder<?>) mth
                .invoke(Class.forName(record.key()).newInstance()))
                .mergeFrom(by, 8, by.length - 8).build();
            Misc.exeBiConsumer(c, Net.byte2long(by, 0), msg);
          } catch (Exception e) {
            logger.error("parse message failed, msg: {}, dat: {}", record.key(),
                Net.byte2HexStr(record.value().get()));
          }
        });
      } catch (Exception e) {
        logger.error("%s", Misc.trace(e));
      }
      Misc.sleep(50);
    }
  }

}
