package com.ice.brother.house.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.web.servlet.WebMvcMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author:ice
 * @Date: 2018/6/15 11:36
 */
@EnableEurekaServer
@SpringBootApplication(exclude = {ConfigurationPropertiesRebinderAutoConfiguration.class, WebMvcMetricsAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class})
public class HouseServerApplication {

    public static final AtomicBoolean RUN = new AtomicBoolean(true);

    public static void main(String[] args) {
        SpringApplication.run(HouseServerApplication.class, args);
    }
}
