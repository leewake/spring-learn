package com.pangpang.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

/**
 * Created by leewake on 2017/8/1 0001.
 */

@Aspect
@Service
public class LoggerAdvice {

    //@Before("@within(org.springframework.web.bind.annotation.RestController) && @annotation(loggerManage)")
    //@Before("target(com.pangpang.controller.TestAopController) && @annotation(loggerManage)")
    //@Before("@target(org.springframework.web.bind.annotation.RestController) && @annotation(loggerManage)")
    @Before("within(com.pangpang..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage){
        logger.info("执行" + loggerManage.description() + "开始");
        logger.info(joinPoint.getTarget());
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParames(joinPoint.getArgs()));
    }

    @After("@within(org.springframework.web.bind.annotation.RestController) && @annotation(loggerManage)")
    public void addAfterLogger(JoinPoint joinPoint, LoggerManage loggerManage){
        logger.info("执行" + loggerManage.description() + "结束");
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParames(joinPoint.getArgs()));
    }

    private Logger logger = Logger.getLogger(this.getClass());

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("传入参数[{}] ");
        for (Object obj : parames) {
            param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
        }
        return param.toString();
    }
}
