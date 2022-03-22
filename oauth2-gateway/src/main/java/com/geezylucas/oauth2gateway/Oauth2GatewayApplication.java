package com.geezylucas.oauth2gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Oauth2GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2GatewayApplication.class, args);
    }

}
