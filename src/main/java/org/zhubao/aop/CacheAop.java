package org.zhubao.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CacheAop {
    private Logger logger = Logger.getLogger(getClass());

    @Around("execution(* org.zhubao.service..*.*(..))")
    public Object logCache(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String clazzName = thisJoinPoint.getTarget().getClass().getName();
        String methodName = thisJoinPoint.getSignature().getName();
        long start = System.currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        long time = System.currentTimeMillis() - start;
        logger.info("Operation time :" + time + "ms; Class name : " + clazzName + " ; Method name : " + methodName
                + "()");
        System.out.println("Operation time :" + time + "ms; Class name :" + clazzName + " ; Method name : "
                + methodName + "()");
        return result;
    }
}
