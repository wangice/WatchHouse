package com.ice.brother.house.component.producer;

import com.ice.brother.house.common.event.BaseEvent;
import com.ice.brother.house.common.mq.MessageHandler;
import com.ice.brother.house.common.mq.RocketMQMessage;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * RocketMQ 消息组件
 *
 * @author Charles
 * @email amwfhv@yeah.net
 * @create at 2018/1/2 12:27
 **/
@Component
public class RocketMQMessageHandler implements MessageHandler {

  private static final Logger logger = LoggerFactory.getLogger(RocketMQMessageHandler.class);
  @Autowired
  private DefaultMQProducer defaultMQProducer;

  /**
   * 发送消息
   *
   * @return 发送结果 true(成功)/false(失败)
   */
  @Override
  public boolean sendMessage(BaseEvent rocketMQMessage) {
    boolean flag = false;
    Message message = buildMessage(rocketMQMessage);

    //延时消费
    if (rocketMQMessage.getDelayLevel() > 0) {
      message.setDelayTimeLevel(rocketMQMessage.getDelayLevel());
    }

    if (message != null) {
      try {
        SendResult sendResult = defaultMQProducer.send(message);
        logger.warn("sendMessage Result: {}", sendResult);
        if (sendResult != null && SendStatus.SEND_OK == sendResult.getSendStatus()) {
          flag = true;
        } else {
          logger.warn("sendMessage Failed");
        }
      } catch (MQClientException e) {
        e.printStackTrace();
        logger.warn("sendMessage({},{},{}) MQClientException {}", message.getTopic(),
            message.getTags(), rocketMQMessage, e);
      } catch (RemotingException e) {
        e.printStackTrace();
        logger.warn("sendMessage({},{},{}) RemotingException {}", message.getTopic(),
            message.getTags(), rocketMQMessage, e);
      } catch (MQBrokerException e) {
        e.printStackTrace();
        logger.warn("sendMessage({},{},{}) MQBrokerException {}", message.getTopic(),
            message.getTags(), rocketMQMessage, e);
      } catch (InterruptedException e) {
        e.printStackTrace();
        logger.warn("sendMessage({},{},{}) InterruptedException {}", message.getTopic(),
            message.getTags(), rocketMQMessage, e);
      }
      logger.warn("sendMessage({},{},{})====>{}", message.getTopic(), message.getTags(),
          rocketMQMessage, flag);
      return flag;
    } else {
      logger.warn("Message 数据错误,不予发送");
    }
    return flag;
  }

  /**
   * 构建RocketMQ API发送消息 消息体
   *
   * @param rocketMQMessage 消息对象
   * @return RocketMQ API发送消息 消息体
   */
  private Message buildMessage(RocketMQMessage rocketMQMessage) {
    if (rocketMQMessage != null && !StringUtils.isEmpty(rocketMQMessage.getTopic()) && !StringUtils
        .isEmpty(rocketMQMessage.getTag())) {
      return new Message(rocketMQMessage.getTopic(), rocketMQMessage.getTag(),
          rocketMQMessage.toByteArray());
    }
    return null;
  }
}
