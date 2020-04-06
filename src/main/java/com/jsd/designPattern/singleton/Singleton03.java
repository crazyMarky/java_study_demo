package com.jsd.designPattern.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 静态代码块的饿汉式
 * 在静态代码可以编写一些设置属性的方法，更灵活
 */
public class Singleton03 {
    //使用public，而且是final
    public final static Singleton03 INSTANCE ;

    private String info ;

    static {
        Properties properties = new Properties();
        try {
            //读取配置文件
            properties.load(Singleton03.class.getClassLoader().getResourceAsStream("singleton/kv.properties"));
            //读取值
            String info1 = properties.getProperty("info");
            INSTANCE = new Singleton03(info1);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Singleton03(){

    }

    private Singleton03(String info){
        this.info = info;
    }

    public static Singleton03 getINSTANCE() {
        return INSTANCE;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton03{" +
                "info='" + info + '\'' +
                '}';
    }
}
