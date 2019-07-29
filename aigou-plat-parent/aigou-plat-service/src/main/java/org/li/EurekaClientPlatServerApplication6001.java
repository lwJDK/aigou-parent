package org.li;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2 //开启swagger接口文档
public class EurekaClientPlatServerApplication6001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientPlatServerApplication6001.class, args);
    }
}
