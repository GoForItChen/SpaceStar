package com.chen.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


/**
 * QuartzConfig
 * <p>File：QuartzConfig.java</p>
 * <p>Title: QuartzConfig</p>
 * <p>Description: QuartzConfig</p>
 * <p>Copyright: Copyright (c) 2019/10/25</p>
 * <p>Company: BloCain</p>
 *
 * @author harvey
 * @version 1.0
 */
@Configuration
public class QuartzConfig
{
    // 解决使用@Scheduled创建任务时无法在同一时间执行多个任务的BUG
    // springboot创建的线程池poolSize确实是1，当前活动线程数(activethreads)为1
    @Bean
    public TaskScheduler taskScheduler()
    {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(30);
        return taskScheduler;
    }
}
