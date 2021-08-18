package com.chen.mars;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
@EnableEncryptableProperties
@ImportResource({"classpath*:consumer.xml"})
@EnableDubbo
public class StarApplication {

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb://admin:123456@112.124.25.23:27017/starmongo"));
    }

    public static void main(String[] args) {
        SpringApplication.run(StarApplication.class, args);
    }
}
