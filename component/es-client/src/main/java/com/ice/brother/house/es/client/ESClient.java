package com.ice.brother.house.es.client;

import java.io.IOException;
import java.util.List;

public interface ESClient {

  /**
   * 功能：异步请求post数据.
   */
  <T> void postRquestAsync(String index, T data)
      throws IOException;

  /**
   * 功能：批量插入数据.
   */
  <T> void bulkPustRequest(String index, List<T> sourceData)
      throws IOException;
}
