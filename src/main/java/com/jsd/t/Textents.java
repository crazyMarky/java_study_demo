package com.jsd.t;


import java.io.File;

/**
 * 泛型中的继承关系
 *
 * @author A001007008
 */
public class Textents {
    public static void main(String[] args) {
        /**
         * Pair<Manager> 是Pair<Employee>的一个子类吗？
         * 显然答案是 :不是
         * 无论S和T是什么关系，通常Pair<S>和Pair<T>没有什么联系
         *
         */
        Pair<Manager> managerPair = new Pair<>();
//        Pair<Employee> employeePair =managerPair;
        /**
         * 永远可以将参数化类型转换成一个原始类型
         * 否则会出现转换类型错误
         */
        //明显出现类型转换的错误
        Pair rawBuddies=managerPair;
        rawBuddies.setFirst(new File("/"));

        /**
         * 当然，在同一个T的情况下，泛型类是可以拓展成其他类的
         * 例如
         * ArrayList<T> 实现List<T> 所以ArrayList<Employee> 可以转换成List<Employee>
         */
    }
}
