package com.jsd.function.functionInterDemo;

import java.util.function.Function;

/**
 *
 * @author A001007008
 */
@FunctionalInterface
public interface Function2<T,R> {
    Integer op(Integer x, Integer y, Function<T, R> function);
}
