package com.jsd.designPattern.singleton.test;

import com.jsd.designPattern.singleton.Singleton02;
import com.jsd.designPattern.singleton.Singleton03;

public class TestSingleton03 {
    public static void main(String[] args) {
        Singleton03 instance = Singleton03.INSTANCE;
        System.out.println(instance);

        Singleton03 instance2 = Singleton03.INSTANCE;
        System.out.println(instance2);

        System.out.println(instance == instance2);
    }
}
