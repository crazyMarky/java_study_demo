package com.jsd.designPattern.singleton;

/**
 * 静态内部类实现的懒汉式：
 * 延迟加载，且线程安全
 */
public class Singleton06 {
    private Singleton06(){

    }

    /**
     * 静态内部类只有在调用时才会加载
     */
    private static class Inner{
        private static Singleton06 instance = new Singleton06();

    }

    /**
     * 调用获取方法时才加载实例
     * @return
     */
    public static Singleton06 getInstance()  {
        return Inner.instance;
    }
}
