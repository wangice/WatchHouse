package com.ice.brother.house.app.provider.mapper.ext;

import com.ice.brother.house.app.provider.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author:ice
 * @Date: 2018/6/16 14:46
 */
public interface UserExtMapper {

    /**
     * 通过email查询用户
     */
    User selectUserByEmail(@Param("email") String email);

    /**
     * 通过电话号码查询用户
     */
    User selectUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    /**
     * 更新用户最后登录时间
     */
    int updateLastLoginTime(@Param("userId") String userId, @Param("lastLoginTime") Date date);
}
