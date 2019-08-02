package org.li;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2 //开启swagger接口文档
@EnableFeignClients(basePackages = "org.li.common")
@MapperScan("org.li.mapper")
//feign的client接口不在启动类的同包或子包下，手动配置扫描的包
//@ComponentScan
public class EurekaClientProductServerApplication7000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProductServerApplication7000.class, args);
    }
}
