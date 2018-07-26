package com.ice.brother.house.advert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 11635 on 2018/7/27 0027.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class AdvertWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertWebApplication.class, args);
    }
}
