package com.ice.brother.house.common.event;

import com.ice.brother.house.Misc;
import com.ice.brother.house.common.mq.RocketMQMessage;
import java.io.Serializable;
import java.util.Date;

/**
 * @author:ice
 * @Date: 2018/8/9 17:39
 */
public abstract class BaseEvent implements Serializable, RocketMQMessage {

  /**
   * 序列号
   */
  private String id;
  /**
   * 事件时间
   */
  private Date eventTime;
  /**
   * 描述
   */
  private String description;

  /**
   * 延迟时间
   */
  private int delayLevel;


  public void setDelayLevel(int delayLevel) {
    this.delayLevel = delayLevel;
  }

  public BaseEvent() {
//    this.eventTime = new Date().getTime();
  }

  public Date getEventTime() {
    return eventTime == null ? null : (Date) eventTime.clone();
  }

  public void setEventTime(Date eventTime) {
    this.eventTime = (eventTime == null ? null : (Date) eventTime.clone());
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getDelayLevel() {
    return delayLevel;
  }

  /**
   * 字节数组化
   *
   * @return 字节数组
   */
  @Override
  public byte[] toByteArray() {
    return Misc.object2Bytes(this);
  }

  /**
   * 获取TOPIC
   *
   * @return topic
   */
  @Override
  public abstract String getTopic();

  /**
   * 获取TAG
   *
   * @return Tag
   */
  @Override
  public abstract String getTag();
}
