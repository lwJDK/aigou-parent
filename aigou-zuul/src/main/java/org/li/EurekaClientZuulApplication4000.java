package org.li;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy //开启zuul路由器
@EnableSwagger2 //开启swagger接口文档
public class EurekaClientZuulApplication4000
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaClientZuulApplication4000.class, args);
    }
}
