package com.ice.brother.house.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author:ice
 * @Date: 2018/7/16 14:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"/spring/spring-dao.xml", "classpath:application-context.xml"})
@ActiveProfiles("junit")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    MockitoTestExecutionListener.class, ResetMocksTestExecutionListener.class})
public class BaseTest {

  @Autowired
  protected ApplicationContext ctx;

  @Before
  public void setUp() {
    // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test() {

  }
}
