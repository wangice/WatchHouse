package com.ice.brother.house.advert.provider.impl;

import com.ice.brother.house.advert.provider.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 11635 on 2018/7/27 0027.
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public String queryUserByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        return "sorry,not found user by phoneNumber:" + phoneNumber;
    }
}
