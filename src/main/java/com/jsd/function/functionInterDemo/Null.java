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
    public  T of(Object o, Supplier<T> supplier) {
        if (o!=null){
            return supplier.get();
        }
        return null;
    }
    
    public static final <T> T of(Supplier<T> expr, Supplier<T> defaultValue){
        try{
            T result = expr.get();
            
            if(result == null){
                return defaultValue.get();
            }
            return result;
        }catch (NullPointerException|IndexOutOfBoundsException e) {
            return defaultValue.get();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
