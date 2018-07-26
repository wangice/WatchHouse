package com.ice.brother.house.app.provider.service;

import com.ice.brother.house.app.provider.entities.User;

import java.util.Date;

/**
 * @author:ice
 * @Date: 2018/6/16 14:19
 */
public interface UserService {

    /**
     * 通过email查找登录用户
     */
    User userLogin(String email);

    /**
     * 通过电话号码查询用户
     */
    User selectUserByPhoneNumber(String phoneNumber);

    /**
     * 更新最后登录时间
     */
    int updateLastLoginTime(String userId, Date date);
}
