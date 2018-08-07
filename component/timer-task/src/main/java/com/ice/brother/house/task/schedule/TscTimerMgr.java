package com.ice.brother.house.task.schedule;

import com.ice.brother.house.Misc;
import com.ice.brother.house.ODateu;
import com.ice.brother.house.task.actor.Actor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author:ice
 * @Date: 2018/8/4 16:04
 */
@Component
public class TscTimerMgr extends Actor {

  private static final Logger logger = LoggerFactory.getLogger(TscTimerMgr.class);

  /**
   * 每个Tworker工作线程上都有一个定时器管理器.
   */
  public TscTimerMgr mgr = null;
  /**
   * 每个时间轮上的刻度数(一个刻度为1秒).
   */
  private int TICKS = 0x400;
  /**
   * 上次刻度跳动的时间戳.
   */
  private long lts = System.currentTimeMillis();
  /**
   * 时间轮上的指针位置.
   */
  private int slot = 0;

  /**
   * Tworker工作线程上的时间轮.
   */
  public ArrayList<HashSet<TscTimer>> wheel = new ArrayList<HashSet<TscTimer>>();

  public TscTimerMgr() {
    super(ActorType.ITC);
  }

  public void init() {
    this.mgr = new TscTimerMgr();
    for (int c = 0; c < this.TICKS; ++c) {
      this.mgr.wheel.add(new HashSet<TscTimer>()); /* 初始化线程上的时间轮. */
    }
    logger.info("TscTimer manager init successfully.");
  }

  /** ---------------------------------------------------------------- */
  /**                                                                  */
  /** . */
  /**                                                                  */
  /** ---------------------------------------------------------------- */
  /**
   * 跳动一个刻度.
   */
  public void quartz(long now) {
    if (now - this.mgr.lts < ODateu.SECOND) /* 不足一秒. */ {
      return;
    }
    //
    this.mgr.lts = now;
    this.mgr.loop(); /* 执行定时器检查. */
    this.mgr.slot += 1; /* 指针跳动. */
    this.mgr.slot = this.mgr.slot == this.TICKS ? 0 : this.mgr.slot;
  }

  /**
   * 添加一个定时器(此函数只允许在工作线程上调用), 并返回定时器存根. 据此存根可在当前线程上取消定时器.
   */
  public TscTimer addTimer(int sec /* sec秒后超时. */,
      Function<Void, Boolean /* 返回true时表示定时器继续生效. */> cb /* 超时后回调. */) {
    return this.mgr.add(sec, cb);
  }

  /**
   * 添加一个定时器, 一次性使用.
   */
  public TscTimer addTimerOneTime(int sec, /* sec秒后超时. */
      Consumer<Void> cb /* 超时回调. */) {
    return this.addTimer(sec, tm ->
    {
      Misc.exeConsumer(cb, null);
      return false;
    });
  }

  /**
   * 取消一个定时器.
   */
  public void cancelTimer(TscTimer timer) {
    this.future(v -> this.mgr.cancel(timer));
  }

  /** ---------------------------------------------------------------- */
  /**                                                                  */
  /** . */
  /**                                                                  */
  /** ---------------------------------------------------------------- */
  /**
   * 执行定时器检查.
   */
  private void loop() {
    HashSet<TscTimer> solt = this.wheel.get(this.slot);
    ArrayList<TscTimer> arr = new ArrayList<>();
    solt.forEach(v -> arr.add(v)); /* 为避免并发修改, 这里先做一个镜像. */
    //
    ArrayList<TscTimer> tmp = new ArrayList<>();
    for (TscTimer tt : arr) {
      if (tt.loop == 0) /* 无剩余圈数. */ {
        Boolean ret = Misc.exeFunction(tt.cb, null);
        if (ret != null && ret.booleanValue()) /* 继续等待下次超时. */ {
          tmp.add(tt);
        }
        solt.remove(tt);
      } else {
        tt.loop -= 1;
      }
    }
    //
    for (TscTimer tt : tmp) {
      int m = tt.sec % this.TICKS;
      int pos = this.slot + m;
      tt.slot = pos < this.TICKS ? pos /* 放置在指针前面(还未到) */
          : (pos - this.TICKS) /* 放置在指针后面(已经过). */;
      this.wheel.get(tt.slot).add(tt);
    }
  }

  /**
   * 添加一个定时器.
   */
  private TscTimer add(int sec /* sec秒后超时. */, Function<Void, Boolean> cb /* 超时后回调. */) {
    int m = sec % this.TICKS;
    int pos = this.slot + m;
    TscTimer tt = new TscTimer(sec, (sec / this.TICKS),
        pos < this.TICKS ? pos /* 放置在指针前面(还未到) */
            : (pos - this.TICKS) /* 放置在指针后面(已经过). */, cb);
    HashSet<TscTimer> solt = this.wheel.get(tt.slot);
    solt.add(tt);
    return tt;
  }

  /**
   * 取消一个定时器.
   */
  private boolean cancel(TscTimer timer) {
    return this.wheel.get(timer.slot).remove(timer);
  }

  public static class TscTimer {

    /**
     * 定时器附着的工作线程 .
     */
    public int wk;
    /**
     * sec秒后超时.
     */
    public int sec;
    /**
     * 剩余圈数.
     */
    public int loop;
    /**
     * 在时间轮上的槽.
     */
    public int slot;
    /**
     * 超时回调.
     */
    public Function<Void, Boolean> cb;

    public TscTimer(int sec, int loop, int slot, Function<Void, Boolean> cb) {
      this.sec = sec;
      this.loop = loop;
      this.slot = slot;
      this.cb = cb;
    }

    public String toString() {
      return Misc.printf2Str("wk: %d, sec: %d, loop: %d, slot: %d", this.wk, this.sec, this.loop,
          this.slot);
    }
  }
}
