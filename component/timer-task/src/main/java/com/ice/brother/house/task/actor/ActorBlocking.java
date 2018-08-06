package com.ice.brother.house.task.actor;

import com.ice.brother.house.Misc;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author:ice
 * @Date: 2018/8/6 20:18
 */
public class ActorBlocking extends Actor {

  /**
   * 等待处理的Consumer.
   */
  private ConcurrentLinkedQueue<Consumer<Void>> cs = new ConcurrentLinkedQueue<>();
  /**
   * 拥有线程的个数
   */
  private int tc = 1;

  /**
   * cs的size
   */
  private AtomicInteger size = new AtomicInteger(0);

  /**
   * 线程忙？
   */
  public volatile boolean busy = false;


  public ActorBlocking(int tc) {
    super(ActorType.BLOCKING);
    this.tc = tc < 1 ? 1 : tc;
    this.start();
  }

  /**
   * 添加任务.
   */
  public void push(Consumer<Void> c) {
    this.cs.add(c);
    this.size.incrementAndGet();
    synchronized (this) {//通知线程消费信息
      this.notify();
    }
  }

  /**
   * 线程忙?
   */
  public boolean isBusy() {
    return this.busy;
  }

  /**
   * 队列尺寸.
   */
  public int size() {
    return this.size.get();
  }

  /**
   * 启动线程
   */
  private void start() {
    ActorBlocking ab = this;
    ExecutorService ex = Executors.newFixedThreadPool(this.tc);//创建线程池
    for (int i = 0; i < tc; i++) {
      ex.execute(() -> {
        while (true) {
          ab.run();
        }
      });
    }
  }

  /**
   * 抢占式消费任务
   */
  private void run() {
    Consumer<Void> c = this.cs.poll();
    if (c == null) {
      synchronized (this) {
        try {
          this.wait();
        } catch (InterruptedException e) {
        }
      }
      c = this.cs.poll();
    }
    if (c != null) /* 抢占式. */ {
      this.size.decrementAndGet();
      this.busy = true;
      Misc.exeConsumer(c, null);
      this.busy = false;
    }
  }
}
