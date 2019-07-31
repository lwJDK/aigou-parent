package org.li;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2 //开启swagger接口文档
@MapperScan("org.li.mapper")
public class EurekaClientProductServerApplication7000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProductServerApplication7000.class, args);
    }
}
