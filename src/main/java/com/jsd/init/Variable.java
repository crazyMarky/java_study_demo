package com.jsd.init;

/**
 * 变量的作用域、变量的分类、this指向
 *
 * 求输出的结果？
 */
public class Variable {
    static int s;
    int i;
    int j;

    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j){
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        Variable variable1 = new Variable();
        Variable variable2 = new Variable();
        variable1.test(10);
        variable1.test(20);
        variable2.test(30);
        System.out.println(variable1.i+","+variable1.j+","+variable1.s);
        System.out.println(variable2.i+","+variable2.j+","+variable2.s);

        //解析：
        /**
         * main方法的第一句，variable1 = new Variable()，执行< init >()方法，先执行非静态代码块的内容，最后才执行无参构造方法
         * 代码块中，int i = 1;i++;，根据就近原则 ，该代码块中的i为2，不会影响成员变量i，i=0
         * j++使得成员变量j，变为1
         * s++使得类变量，变为1
         * 同理，重复执行一次variable2 = new Variable()
         * 变量i与变量j与variable1一致，将类变量s变为2
         * variable1.test(10)，由于形参名为j，j++只会改变局部变量，不是成员变量j，维持1；i++会让成员变量i+1,所以i=1,类变量s为3
         * variable1.test(20)，由于形参名为j，j++只会改变局部变量，不是成员变量j，维持1；i++会让成员变量i+1,所以i=2,类变量s为4
         * variable1.test(30)，由于形参名为j，j++只会改变局部变量，不是成员变量j，维持1；i++会让成员变量i+1,所以i=1,类变量s为5
         * 最终输出：
         * 2,1,5
         * 1,1,5
         *
         */

    }
}
