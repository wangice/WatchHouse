package com.ice.brother.house.app.provider.service.impl;

import com.ice.brother.house.app.provider.entities.User;
import com.ice.brother.house.app.provider.mapper.ext.UserExtMapper;
import com.ice.brother.house.app.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author:ice
 * @Date: 2018/6/16 14:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public User userLogin(String email) {

        User user = userExtMapper.selectUserByEmail(email);
        return user;
    }

    @Override
    public int updateLastLoginTime(String userId, Date date) {
        return userExtMapper.updateLastLoginTime(userId, date);
    }
}
