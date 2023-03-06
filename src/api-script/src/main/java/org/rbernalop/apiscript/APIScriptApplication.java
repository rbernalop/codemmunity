package org.rbernalop.apiscript;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"org.rbernalop.shared", "org.rbernalop.apiscript"})
public class APIScriptApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIScriptApplication.class, args);
    }
}
