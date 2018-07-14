package com.ice.brother.house.es.client;

import com.ice.brother.house.es.client.core.ESClientFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.index.IndexRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

@Service
public class ESClientImpl implements ESClient {

  private static final Logger logger = LoggerFactory.getLogger(ESClientImpl.class);
  @Autowired
  private ESProperties esProperties;

  //增加配置properties的方法(方便单元测试注入)暂未实现注入单元测试的bean到Mock变量值中的方法.
  public void setupProperties(ESProperties properties) throws IOException {
    esProperties = properties;
  }

  //异步请求post数据.
  @Override
  public <T> void postRquestAsync(String index, T data) throws IOException {
    String indexWithPostfix = setIndexPostfix(index);
    // 将保存数据以Map格式关联到请求
    IndexRequest request = new IndexRequest(indexWithPostfix, index);
    Map<String, Object> source = beanToMap(data);
    request.source(source);
    ESClientFactory.getInstance(esProperties).requestES(request);
  }

  // 批量插入数据.
  @Override
  public <T> void bulkPustRequest(String index, List<T> sourceData) throws IOException {
    String indexWithPostfix = setIndexPostfix(index);
    // 将其他请求绑定到批量请求上，不止是indexRequest，其他查询，获取，删除操作均可以添加到批量请求，统一操作
    ESClientFactory clientFactory = ESClientFactory.getInstance(esProperties);
    for (T data : sourceData) {
      // 将保存数据以Map格式关联到请求
      Map<String, Object> source = beanToMap(data);
      IndexRequest request = new IndexRequest(indexWithPostfix, index);
      request.source(source);
      clientFactory.requestES(request);
    }
  }

  /**
   * 将对象装换为map
   */
  public <T> Map<String, Object> beanToMap(T bean) {
    Map<String, Object> map = new HashMap<>();
    if (bean != null) {
      if (bean instanceof String) {
        map.put(esProperties.getStrKey(), bean);
      } else if (bean instanceof Map) {
        map = (Map<String, Object>) bean;
      } else {
        BeanMap beanMap = BeanMap.create(bean);
        for (Object key : beanMap.keySet()) {
          map.put(key + "", beanMap.get(key));
        }
      }
    }
    return map;
  }

  private String setIndexPostfix(String index) {
    return index + "_" + new SimpleDateFormat(esProperties.getTimeFormat())
        .format(System.currentTimeMillis());
  }
}