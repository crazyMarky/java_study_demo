package com.jsd.init;

public class Son extends Father{
    private int i = test();
    private static int j = method();

    static {
        System.out.println("(6)");
    }

    Son(){
        System.out.println("(7)");
    }

    {
        System.out.println("(8)");
    }

    public int test(){
        System.out.println("(9)");
        return 1;
    }

    public static int method(){
        System.out.println("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println();
        Son son1 = new Son();

        //解题思路：

        //第一步：类初始化：
        //从main方法存在的类开始加载，也就是Son类，但是加载前要先加载他的父类father的<clinit>()方法
        //一个类的初始化就是执行< clinit >()方法，且只会执行一次
        //< clinit >()方法由静态类变量显式赋值代码和静态代码块组成，从上到下按顺序执行
        //所以先输出(5)(1)
        //然后回到子类的初始化，输出(10)(6)

        //第二步：实例的初始化
        //执行实例初始化是执行<init>()方法，<init>()方法可能重载有多个，有几个构造器就有几个< init >()方法
        // < init >()方法由非静态实例变量显式赋值代码和非静态代码块、对应构造器代码组成
        // 非静态实例变量显式赋值代码和非静态代码块从上到下顺序执行，而对应构造器的代码最后执行
        // 每次创建实例对象，调用对应构造器，执行的就是对应的< init >()方法
        // < init >()方法的首行是super()或super(实参列表)，即对应父类的< init >()方法
        // 所以执行new Son()时，先执行父类的非静态实例变量显式赋值和非静态代码块
        //执行父类 private int i = test();时，由于该方法已被子类重写，this的指向为子类，
        //因此执行的是子类的method方法，输出(9)
        //然后执行到父类的非静态代码块，输出(3)
        //最后才会执行父类的无参构造函数，输出(2)
        //回到子类，遇到子类的 private int i = test();时，输出(9)
        //执行子类的非静态代码块，输出(8)
        //最后才执行子类的无参构造函数，输出(7)

        /**最终结果：
         (5)
         (1)
         (10)
         (6)
         (9)
         (3)
         (2)
         (9)
         (8)
         (7)

         (9)
         (3)
         (2)
         (9)
         (8)
         (7)
         */
    }
}
