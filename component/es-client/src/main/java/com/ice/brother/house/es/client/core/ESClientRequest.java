package com.ice.brother.house.es.client.core;

import com.ice.brother.house.Misc;
import com.ice.brother.house.es.client.ESProperties;
import java.io.IOException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ESClientRequest implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(ESClientRequest.class);
  private Thread t;
  private long counts = 0; //批量请求ES的数量限制.
  private long startTime = 0;//批量请求ES的起始时间.
  private long bulkCount = 0;//请求ES的数据量上限(缓存列表数目超过此数目，则将请求ES进行处理).
  private long bulkTime = 0;//请求ES的数据时间上限(缓存数据时间超过此限制，则将请求ES进行处理).
  private ESProperties properties = null;
  private static BulkRequest bulkRequest = null;

  protected ESClientRequest(ESProperties esProperties) {
    bulkRequest = new BulkRequest();
    this.properties = esProperties;
    this.bulkTime = esProperties.getBulkTime();
    this.bulkCount = esProperties.getBulkCount();
  }

  public void run() {
    ThreadLoop:
    while (true) {
      long currTime = System.currentTimeMillis();
      //如果数量超过限制，或时间超过限制，则自动提交到ES.
      while ((currTime - startTime) < bulkTime && counts < bulkCount) {
        // 让线程睡眠一会
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          logger.error("Thread  interrupted:{}.", e);
        }
        currTime = System.currentTimeMillis();
      }

      //将数据提交给ES后，清空缓存中的数据.
      sendRequest();
      cleanRequestData();
      if (ESClientFactory.breakFlag) {
        logger.debug("写入ES的线程被终端.");
        break ThreadLoop;
      }
    }
  }

  protected void start() {
    if (t == null) {
      startTime = System.currentTimeMillis();
      t = new Thread(this);
      logger.debug("初始化写入ES的线程. ThreadKey=" + t.getId());
      t.start();
    }
  }

  /**
   * 功能：添加数据到缓存数据列表.
   */
  protected void addDoc(DocWriteRequest request) {
    // 将其他请求绑定到批量请求上，不止是indexRequest，其他查询，获取，删除操作均可以添加到批量请求，统一操作
    bulkRequest.add(request);
    counts++;
  }

  /**
   * 功能：触发请求到ES.
   */
  private void sendRequest() {
    RestHighLevelClient client = ESClientFactory.getInstance(properties).getHighLevelClient();
    client.bulkAsync(bulkRequest, new ActionListener<BulkResponse>() {
      @Override
      public void onResponse(BulkResponse bulkItemResponses) {
        logger.debug("Batch Store Result: {}", bulkItemResponses.status().getStatus());
        try {
          client.close();
        } catch (IOException e) {

        }
      }

      @Override
      public void onFailure(Exception e) {
        logger.debug("Batch Store Failed: {}", Misc.trace(e));
        try {
          client.close();
        } catch (IOException e2) {
        }
      }
    });

    //TODO 对响应结果进行处理(暂不需要处理).
  }

  private void cleanRequestData() {
    bulkRequest = new BulkRequest();
    counts = 0;
    startTime = System.currentTimeMillis();
  }
}