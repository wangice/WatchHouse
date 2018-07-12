package com.ice.brother.house.es.client.core;

import com.ice.brother.house.es.client.ESProperties;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClient.FailureListener;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:ice
 * @Date: 2018/7/9 19:47
 */
public class ESClientFactory {

  private static final Logger logger = LoggerFactory.getLogger(ESClientFactory.class);

  protected static boolean breakFlag = false;//中断线程.
  private static RestClientBuilder builder;
  private static ESClientFactory factory;
  private static ESClientRequest clientThread = null;
  private static RestClient restClient = null;

  private ESClientFactory(ESProperties esProproties) {
    HttpHost httpPost = new HttpHost(esProproties.getHost(), esProproties.getPort(),
        esProproties.getSchema());
    builder = RestClient.builder(httpPost);
    setFailureListener();
    //http延时设置
    if (esProproties.isUniqueConnectTimeConfig()) {
      setConnectionTimeoutConfig(esProproties);
    }
    //主要关于httpClient的连接数配置和权限配置
    builder.setHttpClientConfigCallback(new ESAuthConfigCallback(httpPost, esProproties));
    clientThread = new ESClientRequest(esProproties);
    clientThread.start();
  }

  /**
   * 功能：获取客户端工厂实例.
   */
  public static ESClientFactory getInstance(ESProperties esProperties) {
    if (factory == null) {
      try {
        Thread.sleep(300);
        synchronized (ESClientFactory.class) {
          if (factory == null) {
            factory = new ESClientFactory(esProperties);
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return factory;
  }

  /**
   * 功能：请求ES对数据进行处理.
   */
  public void requestES(DocWriteRequest request) {
    // 将其他请求绑定到批量请求上，不止是indexRequest，其他查询，获取，删除操作均可以添加到批量请求，统一操作
    clientThread.addDoc(request);
  }

  public RestClient getClient() {
    return builder.build();
  }

  public RestHighLevelClient getHighLevelClient() {
    return new RestHighLevelClient(builder);
  }

  /**
   * 功能：中断ES数据循环等待线程.
   */
  public void interruptThread() {
    breakFlag = true;
  }

  public void close() {
    if (restClient != null) {
      try {
        restClient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 设置超时设置
   */
  private void setConnectionTimeoutConfig(ESProperties esProproties) {
    builder.setRequestConfigCallback(new RequestConfigCallback() {
      @Override
      public Builder customizeRequestConfig(Builder builder) {
        //设置超时默认1s
        builder.setConnectTimeout(esProproties.getConnectTimeOut());
        //设置socket超时30s
        builder.setSocketTimeout(esProproties.getSocketTimeOut());
        builder.setConnectionRequestTimeout(esProproties.getConnReqTimeOut());
        return builder;
      }
    }).setMaxRetryTimeoutMillis(esProproties.getSocketTimeOut());
  }

  /**
   * 设置监听，节点故障时触发.
   */
  private void setFailureListener() {
    builder.setFailureListener(new FailureListener() {
      @Override
      public void onFailure(HttpHost host) {
        logger.warn("Esclient ，节点故障, host={}", host.toString());
      }
    });
  }


}
