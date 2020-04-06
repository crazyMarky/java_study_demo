package com.jsd.designPattern.singleton;

/**
 * 双重锁校验的懒汉式：
 * 延迟加载，且线程安全
 */
public class Singleton05 {
    private static Singleton05 instance ;
    private Singleton05(){

    }

    /**
     * 调用获取方法时才加载实例
     * @return
     */
    public static Singleton05 getInstance()  {
        //双重锁校验，提高了性能和安全
        if (null == instance){
            synchronized (Singleton05.class){
                if (null == instance){
                    try {
                        //设置一些阻碍，更容易暴露多线程中的问题
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton05();
                }
            }
        }
        return instance;
    }
}
