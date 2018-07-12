package com.ice.brother.house.mqproducer;

import java.util.Properties;
import javax.annotation.PreDestroy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author:ice
 * @Date: 2018/7/11 15:39
 */
@Configuration
@PropertySource(value = "classpath:kafka_${spring.profiles.active}.properties")
public class KafkaConfiguration {

  @Value("${bootstrap.servers}")
  private String servers;
  @Value("${acks}")
  private String acks;
  @Value("${retries}")
  private int retries;
  @Value("${retry.backoff.ms}")
  private int backoff;
  @Value("${batch.size}")
  private int batchSize;
  @Value("${linger.ms}")
  private int linger;
  @Value("${buffer.memory}")
  private int bufferMemory;
  @Value("${key.serializer}")
  private String keySerializer;
  @Value("${valueSerializer}")
  private String valueSerializer;

  private KafkaProducer<String, Bytes> proc;


  @Bean
  public KafkaProducer<String, Bytes> kafkaProducer() {
    Properties pro = new Properties();
    pro.put("bootstrap.servers", servers);
    pro.put("acks", acks);
    pro.put("retries", retries);
    pro.put("retry.backoff.ms", backoff);
    pro.put("batch.size", batchSize);
    pro.put("linger.ms", linger);
    pro.put("buffer.memory", bufferMemory);
    pro.put("key.serializer", keySerializer);
    pro.put("value.serializer", valueSerializer);
    proc = new KafkaProducer<>(pro);
    return proc;
  }

  @PreDestroy
  public void destroy() {
    if (proc != null) {
      proc.close();
    }
  }
}
