package org.rbernalop.apiexecution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan({"org.rbernalop.shared", "org.rbernalop.apiexecution"})
public class APIExecutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(APIExecutionApplication.class, args);
    }
}
