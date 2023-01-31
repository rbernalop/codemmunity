package org.rbernalop.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class APIGatewayApplication {
    public static void main(String[] args) {
        System.out.println("API Gateway");
        SpringApplication.run(APIGatewayApplication.class, args);
    }
}
