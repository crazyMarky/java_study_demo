package com.jsd.designPattern.singleton.test;

import com.jsd.designPattern.singleton.Singleton01;

public class TestSingleton01 {
    public static void main(String[] args) {
        Singleton01 instance = Singleton01.INSTANCE;
        System.out.println(instance);

        Singleton01 instance2 = Singleton01.INSTANCE;
        System.out.println(instance2);

        System.out.println(instance == instance2);
    }
}
