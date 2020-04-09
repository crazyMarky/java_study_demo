package com.jsd.jvm.classload;

/**
 * 类的加载、连接与初始化
 * 1、加载：查找并加载类的二进制数据
 * 2、连接：
 * 验证：确保被加载的类的正确性
 * 准备：为类的静态变量分配内存，并将其初始化为默认值
 * 解析：把类中的符号引用转换为直接引用
 * 3、初始化：为类中的符号引用赋予正确的初始值
 *
 * 本题涉及到考察连接的准备阶段和初始化阶段要做的事情
 */
public class Test5 {
    public static void main(String[] args) {

        System.out.println(Singleton.counter1);
        System.out.println(Singleton.counter2);
    }

}

class Singleton{

     static int counter1;

    private static Singleton singleton = new Singleton();

    private Singleton(){
        counter1++;
        counter2++;
    }

     static int counter2 = 0;

    public static Singleton getInstance(){
        return singleton;
    }
}
