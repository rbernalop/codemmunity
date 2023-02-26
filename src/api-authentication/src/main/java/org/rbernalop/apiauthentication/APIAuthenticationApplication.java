package org.rbernalop.apiauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan({"org.rbernalop.shared", "org.rbernalop.apiauthentication"})
public class APIAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIAuthenticationApplication.class, args);
    }
}
