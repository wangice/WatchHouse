package com.ice.brother.house.advert.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 11635 on 2018/7/27 0027.
 */

@Component
//@FeignClient(value = "house-app-web", fallback = UserServiceImpl.class)
public interface UserService {

    @GetMapping("/user/queryPhonenumber")
    String queryUserByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber);
}
