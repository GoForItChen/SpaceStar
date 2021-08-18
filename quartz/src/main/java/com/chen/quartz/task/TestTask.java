package com.chen.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * TestTask
 * <p>Copyright: Copyright (c) 2021/03/01</p>
 *
 * @author harvey
 * @version 1.0
 */

@Slf4j
@Component
public class TestTask {

    private final Logger logger = LoggerFactory.getLogger(TestTask.class);

    /**
     * //     * 定时任务测试
     * //     * @throws RuntimeException
     * //
     */
    @Scheduled(initialDelay = 2000, fixedDelay = 1000 * 60 * 60)
    public void test() throws RuntimeException {
        logger.info("测试定时任务");
    }

}
