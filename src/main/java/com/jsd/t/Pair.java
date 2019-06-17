package com.jsd.t;

/**
 * 泛型的Demo
 * Demo选自Java核心技术卷一
 *
 * @author A001007008
 */
public class Pair<T> {
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

    public Pair() {
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
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
}
