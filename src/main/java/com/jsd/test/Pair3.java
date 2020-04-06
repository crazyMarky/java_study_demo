package com.jsd.test;

import java.util.Objects;

/**
 * 泛型的Demo
 * Demo选自Java核心技术卷一
 *
 * @author A001007008
 */
public class Pair3<T> {
    /**
     * 创建一个普通泛型类
     * T代表着任意类型
     * 一般用E表示集合的元素类型
     * K和V分别表示表的关键字于值的类型
     * T(U和S)表示任意类型
     * Pair<T，U>
     */
    private T first;

    private T second;

    public Pair3() {
    }

    public Pair3(T first, T second) {
        this.first = first;
        this.second = second;
        //以下两种泛型实例化是错误的
//        this.first =new T();
//        T.class.newInstance();
    }

    /**
     * 必须向以下这样设计api才可以
     *
     * @param cl
     * @param <T>
     * @return
     */
    public static <T> Pair<T> makePair(Class<T> cl) {
        try {
            return new Pair<>(cl.newInstance(), cl.newInstance());
        } catch (Exception e) {
            return null;
        }
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    /**
     *
     * 显然这个方法并不能通过编译，理由很简单
     * 当泛型擦除的时候，这个方法与Object的方法存在多态
     * 出现了冲突
     */
//    @Override
//    public boolean equals(T o) {
//        return first.equals(o)&&second.equals(o);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
