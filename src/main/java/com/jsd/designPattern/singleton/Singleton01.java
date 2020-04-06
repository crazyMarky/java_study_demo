package com.jsd.designPattern.singleton;

/**
 * 最简饿汉式：
 * 直接创建对象，不存在线程问题
 *
 */
public class Singleton01 {
    //使用public，而且是final
    public final static Singleton01 INSTANCE = new Singleton01();

    //私有构造
    private Singleton01(){

    }

}
