package org.rbernalop.apiauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class APIAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIAuthenticationApplication.class, args);
    }
}
