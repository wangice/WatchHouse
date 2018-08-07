package com.ice.brother.house.task.schedule;

import com.ice.brother.house.Misc;
import com.ice.brother.house.task.actor.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 工作线程,可以用于定时管理器
 *
 * @author:ice
 * @Date: 2018/8/7 17:11
 */
@Component
public class Tworker extends Actor {

  @Value("${tworker.quartz}")
  private long quartz;

  @Autowired
  private TscTimerMgr tscTimerMgr;

  public long clock = System.currentTimeMillis();

  public Tworker() {
    super(ActorType.ITC);
  }

  /**
   * 定时震荡
   */
  public void hold() {
    while (true) {
      Misc.sleep(quartz);
      this.clock = System.currentTimeMillis();
      this.future(v -> {
        tscTimerMgr.quartz(clock);
      });
    }
  }

}
