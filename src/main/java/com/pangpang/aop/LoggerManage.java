package com.pangpang.aop;

import java.lang.annotation.*;

/**
 * Created by leewake on 2017/8/1 0001.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {
    String description() default "string";
}
