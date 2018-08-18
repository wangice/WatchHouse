package com.ice.brother.house.common.mq;

import com.ice.brother.house.common.event.BaseEvent;

/**
 * @author:ice
 * @Date: 2018/8/9 17:38
 */
public interface MessageHandler {

  /**
   * 发送消息
   *
   * @return 发送结果 true(成功)/false(失败)
   */
  boolean sendMessage(BaseEvent baseEvent);
}
