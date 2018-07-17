package com.ice.brother.house.app.provider.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author:ice
 * @Date: 2018/6/16 12:28
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ice.brother.house.app.provider.mapper","com.ice.brother.house.app.provider.mapper.ext"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {

  private static final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

  @Autowired
  private MySqlConfigProperties mySqlConfigProperties;

  @Autowired
  private DataSourceProperties dataSourceProperties;


  @Bean("dataSource")
  public DataSource dataSource() {
    DruidDataSource ds = new DruidDataSource();
    logger.debug("DruidDataSource开始连接数据源...");
    ds.setDriverClassName(this.dataSourceProperties.getDriverClass());
    ds.setUrl(mySqlConfigProperties.getUrl());
    ds.setUsername(mySqlConfigProperties.getUsername());
    ds.setPassword(mySqlConfigProperties.getPassword());
    ds.setMaxActive(this.dataSourceProperties.getMaxActive());
    ds.setMaxWait(this.dataSourceProperties.getMaxWait());
    ds.setInitialSize(this.dataSourceProperties.getInitialSize());
    ds.setValidationQuery(this.dataSourceProperties.getValidationQuery());
    ds.setPoolPreparedStatements(this.dataSourceProperties.isPoolPreparedStatements());
    ds.setMaxPoolPreparedStatementPerConnectionSize(
        this.dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
    ds.setTestWhileIdle(true);
    ds.setTestOnBorrow(false);
    ds.setTestOnReturn(false);
    try {
      ds.setFilters(this.dataSourceProperties.getFilters());
    } catch (SQLException e) {
      logger.error("发生异常：", e);
    }
    ds.setConnectionProperties(this.dataSourceProperties.getConnectionProperties());
    return ds;
  }


  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("com.ice.brother.house.app.provider.entities");
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      sqlSessionFactoryBean
          .setMapperLocations(resolver.getResources("classpath*:mapper/**/*Mapper.xml"));
      SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
      sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
      return sqlSessionFactory;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Bean(name = "sqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(
      @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean(name = "annotationDrivenTransactionManager")
  public PlatformTransactionManager annotationDrivenTransactionManager(
      @Qualifier("dataSource") DataSource dmDataSource) {
    return new DataSourceTransactionManager(dmDataSource);
  }

}
