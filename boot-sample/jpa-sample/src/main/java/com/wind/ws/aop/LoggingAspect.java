package com.wind.ws.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Title: LoggingAspect
 * @Package com.wind.jpa.aop
 * @Description: 性能日志记录
 * @author wind
 * @date 2018/10/19 9:24
 * @version V1.0
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger("speedLog");


    /**
     * 性能日志切面
     */
    @Pointcut("execution(* com.wind.ws.controller..*.*(..)) || execution(* com.wind.ws.dao..*.*(..))")
    public void logAspect() {

    }

    /**
     * 打印日志切面
     */
    @Pointcut("execution(* com.wind.ws.controller..*.*(..))")
    public void printAspect() {

    }

    /**
     * 拦截方法，计算方法执行耗时并写入日志文件
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logAspect()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return point.proceed();
        } finally {
            Signature signature = point.getSignature();
            logger.info("{} {} timeConsuming：{}", signature.getDeclaringTypeName().substring(signature.getDeclaringTypeName().lastIndexOf(".") + 1),
                    signature.getName(), (System.currentTimeMillis() - startTime) + " ms");
        }

    }

    /**
     * 打印方法返回值
     * @param joinPoint
     * @param returnValue
     * @throws Throwable
     */
    @AfterReturning(value = "printAspect()",returning="returnValue")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) throws Throwable {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        String simpleName = targetClass.getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Logger log = LoggerFactory.getLogger(targetClass);
        log.info("{}.{} return value is {}", simpleName, methodName, returnValue);
    }
}
