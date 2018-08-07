package com.ice.brother.house.task.schedule;

import com.ice.brother.house.Misc;
import com.ice.brother.house.task.actor.Actor;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;

/**
 * 工作线程,可以用于定时管理器
 *
 * @author:ice
 * @Date: 2018/8/7 17:11
 */
@Component
@AutoConfigureAfter({TscTimerMgr.class})
public class Tworker extends Actor {

  @Value("${tworker.quartz}")
  private long quartz;

  @Autowired
  private TscTimerMgr tscTimerMgr;

  /**
   * 系统中经常用到System.currentTimeMillis(),减少消耗.
   */
  public long clock = System.currentTimeMillis();

  public Tworker() {
    super(ActorType.ITC);
  }

  /**
   * 初始化并
   */
  @PostConstruct
  public void run() {
    tscTimerMgr.init();//初始化定时器.
    new Thread(() -> this.hold()).start();
  }

  /**
   * 定时器震荡.
   */
  public void hold() {

    while (true) {
      Misc.sleep(quartz);
      this.clock = System.currentTimeMillis();
      this.future(v -> tscTimerMgr.quartz(clock));
    }
  }

}
