package com.jsd.algorithmic.recursion;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

/**
 * 请问：n阶台阶，一次走一步或两步，有多少种走法？
 * 递归实现 01
 * 迭代实现 02
 */
public class Footstep01 {

    @Test
    public void test(){
        StopWatch watch = new StopWatch("测试运行时间");
        watch.start("递归实现");
        int f1 = f01(10);
        watch.stop();

        watch.start("迭代实现");
        long f02 = f02(50);
        watch.stop();

        System.out.println(watch.prettyPrint());
    }

    //递归实现 01
    public int f01(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return n;
        }

        return f01(n-1) + f01(n-2);
    }

    /**
     * 迭代实现 02
     * @param n
     * @return
     */
    public long f02(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return n;
        }
        //初始化：走第一级台阶有一种走法，走第二级台阶有两种走法
        int first = 1, second = 2;
        int third = 0;
        //把每次计算的结果保存在变量中，避免重复计算
        for (int i = 3; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

}
