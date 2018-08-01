package com.ice.brother.house.bos.provider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author:ice
 * @Date: 2018/6/16 12:25
 */
@Configuration
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
public class DataSourceProperties {

  public static final String PREFIX = "spring.datasource";

  private String driverClass;

  private String validationQuery;

  private boolean poolPreparedStatements = true;

  private int maxPoolPreparedStatementPerConnectionSize = 20;

  private int initialSize = 20;

  private int maxActive = 100;

  private int maxWait = 60000;

  private String filters;

  private String connectionProperties;

  public String getDriverClass() {
    return driverClass;
  }

  public void setDriverClass(String driverClass) {
    this.driverClass = driverClass;
  }

  public String getValidationQuery() {
    return validationQuery;
  }

  public void setValidationQuery(String validationQuery) {
    this.validationQuery = validationQuery;
  }

  public boolean isPoolPreparedStatements() {
    return poolPreparedStatements;
  }

  public void setPoolPreparedStatements(boolean poolPreparedStatements) {
    this.poolPreparedStatements = poolPreparedStatements;
  }

  public int getMaxPoolPreparedStatementPerConnectionSize() {
    return maxPoolPreparedStatementPerConnectionSize;
  }

  public void setMaxPoolPreparedStatementPerConnectionSize(
      int maxPoolPreparedStatementPerConnectionSize) {
    this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
  }

  public int getInitialSize() {
    return initialSize;
  }

  public void setInitialSize(int initialSize) {
    this.initialSize = initialSize;
  }

  public int getMaxActive() {
    return maxActive;
  }

  public void setMaxActive(int maxActive) {
    this.maxActive = maxActive;
  }

  public int getMaxWait() {
    return maxWait;
  }

  public void setMaxWait(int maxWait) {
    this.maxWait = maxWait;
  }

  public String getFilters() {
    return filters;
  }

  public void setFilters(String filters) {
    this.filters = filters;
  }

  public String getConnectionProperties() {
    return connectionProperties;
  }

  public void setConnectionProperties(String connectionProperties) {
    this.connectionProperties = connectionProperties;
  }
}
