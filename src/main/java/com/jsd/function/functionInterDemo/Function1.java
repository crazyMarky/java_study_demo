package com.jsd.function.functionInterDemo;

import java.util.function.Predicate;


/**
 *
 * @author A001007008
 */
@FunctionalInterface
public interface Function1 {
      void op(Object o, Predicate predicate);
}
