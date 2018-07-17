package com.ice.brother.house.app.dao;

import com.ice.brother.house.app.BaseTest;
import com.ice.brother.house.app.provider.entities.User;
import com.ice.brother.house.app.provider.mapper.ext.UserExtMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author:ice
 * @Date: 2018/7/16 15:27
 */
public class UserExtMapperTest extends BaseTest {

  private UserExtMapper userExtMapper;

  @Before
  public void setUp() {
    userExtMapper = (UserExtMapper) ctx.getBean("userExtMapper");
  }

  @Test
  public void selectUserByEmail() {
    User user = userExtMapper.selectUserByEmail("1163569270@qq.com");
    Assert.assertNotNull(user);
  }

}
