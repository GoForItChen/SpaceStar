package com.chen.mars;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource({"classpath*:provider.xml"})
@EnableDubbo
@EnableEncryptableProperties
public class StarRPCServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        SpringApplication.run(StarRPCServer.class, args);
    }
}
