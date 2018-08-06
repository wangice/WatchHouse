package com.ice.brother.house.task;

import java.util.function.Consumer;

/**
 * @author:ice
 * @Date: 2018/8/4 16:04
 */
public abstract class Actor {

  public enum ActorType {
    TIC,  /* 立刻消费. */

    BLOCKING;  /* 阻塞.*/
  }

  /**
   * actor类型.
   */
  public ActorType type;
  /**
   * actor名.
   */
  public String name;

  public Actor(ActorType type) {
    this.type = type;
    this.name = this.getClass().getSimpleName();
  }

  public void future(Consumer<Void> c) {
    if (this.type.ordinal() == ActorType.BLOCKING.ordinal()) {//阻塞

    } else {
      
    }
  }

}
