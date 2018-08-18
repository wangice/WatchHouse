package com.ice.brother.house.consumer.configuration;

import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Spring 线程池配置
 *
 * @author Charles
 * @Email amwfhv@yeah.net
 * @Create at 2017/10/25 09:42
 **/
@Configuration
public class ThreadPoolConfiguration {

  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Bean
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    this.threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    this.threadPoolTaskExecutor.setCorePoolSize(20);
    this.threadPoolTaskExecutor.setMaxPoolSize(100);
    this.threadPoolTaskExecutor.setKeepAliveSeconds(100);
    this.threadPoolTaskExecutor.setQueueCapacity(2000);
    this.threadPoolTaskExecutor
        .setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    return this.threadPoolTaskExecutor;
  }

  @PreDestroy
  public void destroy() {
    if (threadPoolTaskExecutor != null) {
      threadPoolTaskExecutor.shutdown();
    }
  }
}
