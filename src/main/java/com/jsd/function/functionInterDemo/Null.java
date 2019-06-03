package com.jsd.function.functionInterDemo;

import java.util.function.Supplier;

/**
 *
 * @author A001007008
 */
public class Null<T> {

    /**
     * 内置函数接口 举例1：Supplier<T>
     */
    public  Object of(Object o, Supplier<T> supplier) {
        if (o!=null){
            return supplier.get();
        }
        return null;
    }
}
