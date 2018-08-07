package com.ice.brother.house.app.web.core;

import com.ice.brother.house.task.actor.ActorBlocking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/8/7 18:46
 */
@Component
public class AppActorBlocking extends ActorBlocking {

  @Value("${thread.size}")
  private int threadSize;

  public AppActorBlocking() {
    this.setTc(threadSize);//设置线程数量
    this.start();
  }
}
