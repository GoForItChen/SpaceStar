package com.chen.quartz;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * StarQuartzServer
 * <p>File：BrspQuartzServer.java</p>
 * <p>Title: BrspQuartzServer</p>
 * <p>Description: BrspQuartzServer</p>
 * <p>Copyright: Copyright (c) 2019/10/25</p>
 * <p>Company: BloCain</p>
 *
 * @author harvey
 * @version 1.0
 */
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.chen")
public class StarQuartzServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        SpringApplication springApplication = new SpringApplication(StarQuartzServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
