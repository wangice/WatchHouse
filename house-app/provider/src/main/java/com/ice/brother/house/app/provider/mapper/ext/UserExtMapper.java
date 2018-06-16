package com.ice.brother.house.app.provider.mapper.ext;

import com.ice.brother.house.app.provider.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author:ice
 * @Date: 2018/6/16 14:46
 */
public interface UserExtMapper {

  /**
   * 通过email查询用户
   */
  User selectUserByEmail(@Param("email") String email);
}
