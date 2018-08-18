package com.ice.brother.house.common.mq;

import com.ice.brother.house.common.interfaces.ByteArrayAble;

/**
 * @author:ice
 * @Date: 2018/8/9 17:40
 */
public interface RocketMQMessage extends ByteArrayAble {

  /**
   * 获取TOPIC
   *
   * @return topic
   */
  String getTopic();

  /**
   * 获取TAG
   *
   * @return Tag
   */
  String getTag();
}
