package com.example.aop;

import com.example.anno.Log;
import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Autowired  // 自动注入操作日志Mapper
    private OperateLogMapper operateLogMapper;
    //声明Logger对象
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 环绕通知：拦截带有@Log注解的方法
    @Around("@annotation(com.example.anno.Log)")  // 切入点表达式：标注@Log注解的方法
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {  // 连接点对象参数

        //  获取当前操作人ID
        Integer operateEmpId = CurrentHolder.getOperateLog();

        long begin = System.currentTimeMillis();  // 记录方法开始时间戳
        Object result = joinPoint.proceed();  // 执行目标方法并获取返回值
        long end = System.currentTimeMillis();  // 记录方法结束时间戳

        // 构建操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);  // 设置操作人ID
        operateLog.setOperateTime(LocalDateTime.now());  // 设置当前时间为操作时间
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());// 通过连接点获取目标类全限定名
        operateLog.setMethodName(joinPoint.getSignature().getName()); // 通过连接点获取方法名称
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));// 将方法参数数组转为字符串
        operateLog.setReturnValue(result != null ? result.toString() : null); // 将返回值转为字符串（简单处理）
        operateLog.setCostTime(end - begin);  // 计算方法耗时（毫秒）

        operateLogMapper.insert(operateLog);  // 调用Mapper插入日志记录

        logger.info("操作日志：{}", operateLog);
        return result;  // 返回目标方法的执行结果
    }
}
