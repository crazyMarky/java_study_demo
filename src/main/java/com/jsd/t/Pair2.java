package com.jsd.t;

/**
 * 虚拟机中并没有泛型对象(语法糖)
 * 无论何时定义一个泛型类型，都自动提供一个相应的原始类型
 * 原始类型就是删去类型参数后的泛型类型名，擦除(erased)类型变量，并替换为限定类型
 * (无限定类型的变量用Object)
 * 下例中就是虚拟机中Pair类
 * 如果声明了限定类型例如
 * <T extends Comparable>
 * 所有的T都会被擦除为Comparable类型
 * (基于父类引用子类)
 * 当有多个限定的时候，编译器在必要时要强制转换成另一类型
 * 为了提高效率，应该将标签接口放在边界列表的末尾
 *
 * @author A001007008
 */
public class Pair2 {

    private Object first;

    private Object second;

    public Pair2(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public Object getSecond() {
        return second;
    }

    public void setSecond(Object second) {
        this.second = second;
    }
}
