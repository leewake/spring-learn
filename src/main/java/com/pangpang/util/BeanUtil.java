package com.pangpang.util;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by leewake on 2017/8/9 0009.
 */

@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static Object getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

}
