package com.jsd.t;

/**
 * 在最后描述最后一个泛型相关的东西
 * 通配符
 *
 * @author A001007008
 */
public class Others {
    public static void main(String[] args) {

        /**
         * 但是有可能会存在类型转换的错误
         * 例如:
         */
        Pair<Manager> managerPair = new Pair<>();
        Pair<? extends Employee> pair = managerPair;
//        pair.setFirst(new Manager());
        Employee first1 = pair.getFirst();
        /**
         * setFirst(? extends Employee)
         * 在这里，编译器只知道这个变量继承至Employee，但不知道确切的类型
         * 但使用getFirst()却不会引起问题，(父类对象引用子类对象)
         */
        /**
         * super则完全相反，super限定为Manager的所有超类
         * 在这种情况下，setFirst是可以准确的赋值
         * 但getFirst的返回值却无法保证了
         * setFirst(? super Manager)
         */
        Pair<? super Manager> pair2=managerPair;
        pair2.setFirst(new Manager());
        Object first = pair2.getFirst();
        /**
         * 还存在一种无限定通配符
         * 这种方法和 Pair有一点不同
         * 他可以用任意Object对象调用原始的Pair类的setObject方法
         */
        Pair<?> pair3=managerPair;
//        pair3.setFirst(new Manager());
        Object first2 = pair3.getFirst();
    }

    /**
     * 这里的?表示的是任何一个Employ类型的子类
     * 和方法中的T extends 很相似
     * 但是这个只限制了变量
     * （由于上一次说到的，不能泛型里面是没有继承关系的，所以用这个实现基础的参数的传递）
     */
    public static void printBuddies(Pair<? extends Employee> pair) {

    }
    /**
     * 同样可以指定，任何类的超类作为参数变量
     */
    public static void printBuddies2(Pair<? super Manager> pair){

    }
    /**
     * 无限定通配符
     */
    public static void printBuddies3(Pair<?> pair){

    }
}
