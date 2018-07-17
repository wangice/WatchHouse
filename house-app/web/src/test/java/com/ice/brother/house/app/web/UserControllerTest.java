package com.ice.brother.house.app.web;

import com.ice.brother.house.Misc;
import com.ice.brother.house.app.BaseControllerTest;
import com.ice.brother.house.app.provider.entities.User;
import com.ice.brother.house.app.provider.service.UserService;
import com.ice.brother.house.app.web.controller.UserController;
import com.ice.brother.house.app.web.core.Rsp;
import com.ice.brother.house.app.web.core.Rsp.RspErr;
import com.ice.brother.house.app.web.rsp.RspLogin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author:ice
 * @Date: 2018/7/17 19:33
 */
public class UserControllerTest extends BaseControllerTest {


  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  private String email = "1163569270@qq.com";
  private User user;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();//实例化请求UserController网络连接

    user = new User();
    user.setId(1);
    user.setEmail(email);
    user.setPwd("123456");
    user.setUserId("123");
  }


  @Test
  public void test() throws Exception {
    Mockito.when(userService.userLogin(email)).thenReturn(user);
    //
    MockHttpServletRequestBuilder mockHttpServletRequestBuilder = buildGetRequestBuilder("/login");
    mockHttpServletRequestBuilder.param("email", email);
    mockHttpServletRequestBuilder.param("pwd", "123456");

    ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);//执行请求
    String result = resultActions.andReturn().getResponse().getContentAsString();
    Rsp rsp = Misc.json2Obj(result, Rsp.class);
    Assert.assertEquals(RspErr.ERR_NONE.getErrCode(), rsp.getErr());
    RspLogin rspLogin = Misc.json2Obj(rsp.getDat().toString(), RspLogin.class);
    Assert.assertEquals("123",rspLogin.getUserId());
  }
}
