package com.lq.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T popBean(Class<T> clazz) {
        return applicationContext == null ? null : applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name) {
        return applicationContext == null ? null : (T) applicationContext.getBean(name);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        return applicationContext == null ? null : (T) applicationContext.getBean(name, clazz);
    }
}
