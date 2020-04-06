package com.jsd.servelt3;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * 容器启动时的实现类
 */
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     * servlet容器启动时会调用这个方法
     * @param set 你需要的类型的实现
     * @param servletContext servlet容器上下文
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyServletContainerInitializer onStartup");
    }
}
