package com.ice.brother.house.mqproducer;

import com.google.protobuf.Message;
import com.ice.brother.house.Misc;
import com.ice.brother.house.Net;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/7/11 15:43
 */
@Component
public class KafkaProducerMessageHandler {

  private static final Logger logger = LoggerFactory.getLogger(KafkaProducerMessageHandler.class);

  @Autowired
  private KafkaProducer<String, Bytes> proc;

  /**
   * 发送消息到kafka
   */
  public boolean send(String topic, Message msg, long ts/*消息时间戳.*/) {
    byte dat[] = msg.toByteArray();
    byte by[] = new byte[8 + dat.length];
    System.arraycopy(Net.long2byte(ts), 0, by, 0, 8);
    System.arraycopy(dat, 0, by, 8, dat.length);
    try {
      this.proc
          .send(new ProducerRecord<>(topic, msg.getClass().getName(), new Bytes(by)), (r, e) -> {
            logger
                .trace("send a msg, type: {}, msg: {}", msg.getClass().getName(), Misc.pb2str(msg));
          });
      return true;
    } catch (Exception e) {
      logger.error("{}", Misc.trace(e));
      return false;
    }

  }

}
