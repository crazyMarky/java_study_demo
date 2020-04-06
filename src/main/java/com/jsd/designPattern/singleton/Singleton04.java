package com.jsd.designPattern.singleton;

/**
 * 普通懒汉式：
 * 延迟加载，但是有线程安全问题
 */
public class Singleton04 {
    private static Singleton04 instance ;
    private Singleton04(){

    }

    /**
     * 调用获取方法时才加载实例
     * @return
     */
    public static Singleton04 getInstance()  {
        if (null == instance){
            try {
                //设置一些阻碍，更容易暴露多线程中的问题
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton04();
        }
        return instance;
    }
}
