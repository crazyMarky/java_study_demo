package com.jsd.jvm.classload;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不仅会触发定义常量的类的初始化
 *
 * 注意：这里指的是将Parant2.str存放到Test2的常量池中，之后Parant2和Test2就没有
 * 任何关系了，甚至Parent2的class文件删除也是不会报错的。
 *
 * 助记符：
 * ldc 表示将int，float或String类型的常量值从常量池中推送至栈顶
 * bipush表示将单字节（-128~127）的常量推送至栈顶
 * sipush表示将一个短整型常量值（-32768~32767）推送至栈顶
 * iconst_1表示将int类型1推送至栈顶（iconst_1 ~ inconst_5）
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(Parant2.i);
    }
}

class Parant2{
    public static final  String str = "hellow world ";

    public static final  short s = 127;

    public static final  int i = 128;

    public static final  int m = 3;

    static {
        System.out.println("Parant2 static block");
    }
}