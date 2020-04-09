package com.jsd.proxy.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理-实现一个日志代理类
 */
public class LogHandler<T> implements InvocationHandler {

    //存放目标类
    private T targetObject;

    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
    public T newProxyInstance(T targetObject){
        this.targetObject=targetObject;
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return (T)(Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),this::invoke));
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret=null;
        try{
            /*原对象方法调用前处理日志信息*/
            System.out.println("satrt-->>");

            //调用目标方法
            ret = method.invoke(targetObject, args);
            /*原对象方法调用后处理日志信息*/
            System.out.println("success-->>");
        }catch(Exception e){
            System.out.println("error-->>");
            e.printStackTrace();
            throw e;
        }
        return ret;
    }


}
