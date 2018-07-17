package com.ice.brother.house.app.service;

import com.ice.brother.house.app.BaseTest;
import com.ice.brother.house.app.provider.entities.User;
import com.ice.brother.house.app.provider.mapper.ext.UserExtMapper;
import com.ice.brother.house.app.provider.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author:ice
 * @Date: 2018/7/17 19:25
 */
public class UserServiceImplTest extends BaseTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserExtMapper userExtMapper;

  private User user;

  @Before
  public void setUp() {
    user = new User();
    user.setId(1);
    user.setPwd("123456");
    user.setEmail("1163569270@qq.com");
  }

  @Test
  public void test() {
    String email = "1163569270@qq.com";
    Mockito.when(userExtMapper.selectUserByEmail(email)).thenReturn(user);
    User user = userService.userLogin(email);
    Mockito.verify(userExtMapper).selectUserByEmail(email);//执行一次
    Assert.assertNotNull(user);
    Assert.assertEquals(1, user.getId().intValue());
  }

}
