package com.vsolopov.myspring.context;

public interface ApplicationContext {

    <T> T getBean(Class<T> beanType);

    Object getBean(String beanName);

    <T> T getBean(String beanName, Class<T> beanType);

}
