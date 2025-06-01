package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LogTest {
    //改成logback技术
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog(){

//        System.out.println(LocalDateTime.now() + " : 开始计算...");
        logger.info("开始计算...");

        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }
        
//        System.out.println("计算结果为: "+sum);
//        System.out.println(LocalDateTime.now() + "结束计算...");
        logger.info("计算结果为: {}", sum);
        logger.info("结束计算...");
    }

}
