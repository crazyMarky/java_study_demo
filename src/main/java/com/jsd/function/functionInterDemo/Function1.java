package com.jsd.function.functionInterDemo;

import java.util.function.Predicate;


@FunctionalInterface
public interface Function1 {
      void op(Object o, Predicate predicate);
}
