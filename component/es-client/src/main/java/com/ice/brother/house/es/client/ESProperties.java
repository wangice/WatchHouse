package com.ice.brother.house.es.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author:ice
 * @Date: 2018/7/9 19:37
 */
@Configuration
@PropertySource(value = {"elasticsearch-${spring.profiles.active}.properties"})
public class ESProperties {

  @Value("${elasticsearch.host}")
  private String host;
  @Value("${elasticsearch.port}")
  private int port;
  @Value("${elasticsearch.schema}")
  private String schema;
  @Value("${elasticsearch.time_format}")
  private String timeFormat;
  @Value("${elasticsearch.http_username}")
  private String username;
  @Value("${elasticsearch.http_password}")
  private String password;
  @Value("${elasticsearch.str_key}")
  private String strKey;
  @Value("${elasticsearch.bulk_count}")
  private int bulkCount;
  @Value("${elasticsearch.unique.connect.config.time}")
  private boolean uniqueConnectTimeConfig;
  @Value("${elasticsearch.unique.connect.config.num}")
  private boolean uniqueConnectNumConfig;
  @Value("${elasticsearch.connect_time_out}")
  private int connectTimeOut;
  @Value("${elasticsearch.socket_time_out}")
  private int socketTimeOut;
  @Value("${elasticsearch.conn_req_time_out}")
  private int connReqTimeOut;
  @Value("${elasticsearch.max_conn_num}")
  private int maxConnNum;
  @Value("${elasticsearch.max_conn_per_route}")
  private int maxConnPerRoute;
  @Value("${elasticsearch.bulk_time}")
  private int bulkTime;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getTimeFormat() {
    return timeFormat;
  }

  public void setTimeFormat(String timeFormat) {
    this.timeFormat = timeFormat;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStrKey() {
    return strKey;
  }

  public void setStrKey(String strKey) {
    this.strKey = strKey;
  }

  public int getBulkCount() {
    return bulkCount;
  }

  public void setBulkCount(int bulkCount) {
    this.bulkCount = bulkCount;
  }

  public boolean isUniqueConnectTimeConfig() {
    return uniqueConnectTimeConfig;
  }

  public void setUniqueConnectTimeConfig(boolean uniqueConnectTimeConfig) {
    this.uniqueConnectTimeConfig = uniqueConnectTimeConfig;
  }

  public boolean isUniqueConnectNumConfig() {
    return uniqueConnectNumConfig;
  }

  public void setUniqueConnectNumConfig(boolean uniqueConnectNumConfig) {
    this.uniqueConnectNumConfig = uniqueConnectNumConfig;
  }

  public int getConnectTimeOut() {
    return connectTimeOut;
  }

  public void setConnectTimeOut(int connectTimeOut) {
    this.connectTimeOut = connectTimeOut;
  }

  public int getSocketTimeOut() {
    return socketTimeOut;
  }

  public void setSocketTimeOut(int socketTimeOut) {
    this.socketTimeOut = socketTimeOut;
  }

  public int getConnReqTimeOut() {
    return connReqTimeOut;
  }

  public void setConnReqTimeOut(int connReqTimeOut) {
    this.connReqTimeOut = connReqTimeOut;
  }

  public int getMaxConnNum() {
    return maxConnNum;
  }

  public void setMaxConnNum(int maxConnNum) {
    this.maxConnNum = maxConnNum;
  }

  public int getMaxConnPerRoute() {
    return maxConnPerRoute;
  }

  public void setMaxConnPerRoute(int maxConnPerRoute) {
    this.maxConnPerRoute = maxConnPerRoute;
  }

  public int getBulkTime() {
    return bulkTime;
  }

  public void setBulkTime(int bulkTime) {
    this.bulkTime = bulkTime;
  }
}
