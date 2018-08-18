package com.ice.brother.house.bos.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.HystrixAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author:ice
 * @Date: 2018/7/31 17:11
 */
@ServletComponentScan
@EnableDiscoveryClient
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class,
    CommonsClientAutoConfiguration.class, HystrixAutoConfiguration.class})
@PropertySource(value = {"provider-${spring.profiles.active}.properties"})
@ImportResource(value = {"classpath:provider.xml"})
public class BosWebApplication {

  public static void main(String[] args) throws UnknownHostException {
    InetAddress address = InetAddress.getLocalHost();
    System.setProperty("serverIP", address.getHostAddress());
    System.setProperty("serverName", address.getHostName());

    SpringApplication.run(BosWebApplication.class, args);
  }

}
