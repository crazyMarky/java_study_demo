package com.jsd.proxy.cglibdynamic;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;


public class Main {
    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        //需要代理的目标类
        enhancer.setSuperclass(HelloService.class);
        //选择增强的函数
        enhancer.setCallback((MethodInterceptor) (sub, method, subArgs, methodProxy) -> {
//            1）sub表示增强的对象，即实现这个接口类的一个对象；
//
//            2）method表示要被拦截的方法；
//
//            3）subArgs表示要被拦截方法的参数；
//
//            4）methodProxy表示要触发父类的方法对象；
            System.out.println("======插入前置通知======");
            Object object = methodProxy.invokeSuper(sub, subArgs);
            System.out.println("======插入后者通知======");
            return object;
        });
        //创建代理对象
        HelloService proxy= (HelloService)enhancer.create();
        //代理对象调用方法，能看到增强的效果
        proxy.sayHello();
        //代理对象不能增强标识为final的方法，不能看到增强的效果
        proxy.sayOthers("2121");
    }
}
