package com.ice.brother.house.app;

import com.ice.brother.house.app.web.AppWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author:ice
 * @Date: 2018/7/17 19:34
 */
@RunWith(PowerMockRunner.class)
@SpringBootTest(classes = {AppWebApplication.class})
@PowerMockIgnore("javax.management.*")
@ActiveProfiles("junit")
@WebAppConfiguration
@TestExecutionListeners({MockitoTestExecutionListener.class, ResetMocksTestExecutionListener.class})
public class BaseControllerTest {

  protected MockMvc mockMvc;

  /**
   * 功能：初始化get请求的builder.
   */
  protected static MockHttpServletRequestBuilder buildGetRequestBuilder(String url) {
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(url);
    mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
    return mockHttpServletRequestBuilder;
  }

  /**
   * 功能：初始化post请求的builder.
   */
  protected static MockHttpServletRequestBuilder buildPostRequestBuilder(String url) {
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(url);
    mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
    return mockHttpServletRequestBuilder;
  }

  /**
   * 功能：初始化post请求的builder.
   */
  protected static MockHttpServletRequestBuilder buildPutRequestBuilder(String url) {
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put(url);
    mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
    return mockHttpServletRequestBuilder;
  }

  /**
   * 功能：初始化post请求的builder.
   */
  protected static MockHttpServletRequestBuilder buildDeleteRequestBuilder(String url) {
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
        .delete(url);
    mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8);
    return mockHttpServletRequestBuilder;
  }

  @Test
  public void test() throws Exception {
  }
}
