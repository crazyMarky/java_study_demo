package com.jsd.concurrent.AQS;

/**
 * volatile关键词的使用以及一些小demo
 * 用于理解AQS中维护的常量
 *
 * @author A001007008
 */
public class VolatileDemo {

    /**
     * volatile的语义是可以使线程内存中的同一变量值和内存中同一变量值是一致的
     * 当存在一个线程读，一个线程更新同一变量的时候，另一个线程的同一变量也会刷新为
     * 最新的变量。
     * 在Java的内存模型中，volatile可以满足可见性和有序性(happen-before中的先读后写)
     * 但是不能满足操作的原子性，所以volatile也是线程不安全的。
     * (以下代码可能存在十分别扭的编写情况)
     *
     */
    private volatile static int count = 0;


    public static void main(String[] args) {
        //开启两个线程对这个常量进行自增操作
        //以下大量的循环输出是用于即使查看
       new Thread(()->{
           try {
               Thread.sleep(200);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           for (int i = 0; i <50 ; i++) {
               //自增50次
               count++;
           }
       }).start();
       new Thread(()->{
           for (int i = 0; i <500; i++) {
               //将这个变量赋值到一个常量，用于之后的输出
               //这个c的常量是用于实验存在线程不安全的情况
               int c = getCount();
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("c is "+c);
               System.out.println("count is "+getCount());
           }
       }).start();
       //d是用于实验getCount方法是值传递还是引用传递
       int d= getCount();
        count=count+1;
        d=d+1;
        for (int i = 0; i <50; i++) {
            //自增50次
            count++;
        }
    }


    public static int getCount() {
        return count;
    }
}
