package com.ice.brother.house.advert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

/**
 * Created by 11635 on 2018/7/27 0027.
 */

@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class AdvertWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertWebApplication.class, args);
    }
}
