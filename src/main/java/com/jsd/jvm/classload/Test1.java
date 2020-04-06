package com.jsd.jvm.classload;

/**
 * 求输出的结果？
 *
 * 考点：
 * 对于静态字段来说，只有定义了该字段的类才会被初始化。即只有主动使用的类才会被初始化，被动使用不会被初始化。
 * 当一个类在初始化时，要求其全部父类都已初始化完毕。
 * -XX:+TraceClassLoading，用于追踪类的加载信息并打印出来
 *
 * -XX:+<option> 表示开启选项
 * -XX:-<option> 表示关闭选项
 * -XX:<option>=<value> 赋值
 */
public class Test1 {

    public static void main(String[] args) {
        //情况1：主动使用Parent1
        System.out.println(Parent1.str);
        System.out.println("------------------------------");
        //情况2：主动使用Child1
        System.out.println(Child1.str);
    }

}

class Parent1{
    public static String str = "hello world";

    static {
        System.out.println("Parent1 static block");
    }
}

class Child1 extends Parent1{
    public static String str = "welcome!";

    static {
        System.out.println("Child1 static block");
    }
}