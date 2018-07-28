package com.ice.brother.house.advert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 11635 on 2018/7/27 0027.
 */
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class, CommonsClientAutoConfiguration.class, HystrixAutoConfiguration.class})
public class AdvertWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvertWebApplication.class, args);
    }
}
