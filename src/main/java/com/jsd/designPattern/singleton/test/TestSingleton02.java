package com.jsd.designPattern.singleton.test;

import com.jsd.designPattern.singleton.Singleton01;
import com.jsd.designPattern.singleton.Singleton02;

public class TestSingleton02 {
    public static void main(String[] args) {
        Singleton02 instance = Singleton02.INSTANCE;
        System.out.println(instance);

        Singleton02 instance2 = Singleton02.INSTANCE;
        System.out.println(instance2);

        System.out.println(instance == instance2);
    }
}
