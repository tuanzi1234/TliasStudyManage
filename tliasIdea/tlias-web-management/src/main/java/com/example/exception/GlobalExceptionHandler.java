package com.example.exception;

import com.example.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //声明logger记录日志
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //返回全局异常
    @ExceptionHandler
    public Result handleException(Exception e) {
        //记录日志
        log.error("服务器发生异常", e);
        //返回错误信息
        return Result.error("服务器发生异常");
    }

    //处理数据重复异常：DuplicateKeyException
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        //记录日志
        log.error("服务器发生异常", e);
        //获取错误数据
        String message = e.getMessage();//获取异常的原始格式
        int index = message.indexOf("Duplicate entry");//定位Duplicate entry的错误信息
        String errorMessage = message.substring(index);//截取Duplicate entry开始的错误信息
        String[] split = errorMessage.split(" ");//以空格分割错误信息，生成数组
        String msg = split[2] + "已存在";//定位到具体重复的数据，并拼接成错误提示信息
        //返回错误信息
        return Result.error(msg);//返回错误信息
    }

    @ExceptionHandler
    public Result handleException(IllegalArgumentException e) {
        //记录日志
        log.error("数值出错", e);
        //返回错误信息
        return Result.error("参数不合法");
    }

    // 处理特定业务规则的RuntimeException
    @ExceptionHandler
    public Result handleBusinessRuleException(RuntimeException e) {
        log.error("业务规则异常：{}", e.getMessage()); // 精准记录日志
        return Result.error(e.getMessage()); // 直接返回异常消息
    }

}
