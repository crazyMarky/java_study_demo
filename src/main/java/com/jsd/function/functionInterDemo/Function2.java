package com.jsd.function.functionInterDemo;

import java.util.function.Function;

@FunctionalInterface
public interface Function2<T,R> {
    Integer op(Integer x, Integer y, Function<T, R> function);
}
