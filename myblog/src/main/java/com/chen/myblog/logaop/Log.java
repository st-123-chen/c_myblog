package com.chen.myblog.logaop;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.chen.myblog.webconfig.*.*(..))")
    public void log(){
        System.out.println("log******");

    }

    @Before("log()")
    public void logbefore(){
        System.out.println("before");


    }

    @After("log()")
    public void logafter(){
        System.out.println("after");


    }

    @AfterReturning(pointcut = "log()",returning = "result")
    public void logrvalue(Object result){
        System.out.println("value");




    }
}
