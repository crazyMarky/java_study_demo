package com.jsd.function.functionInterDemo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A001007008
 */
public class functionDemo {

    public static void main(String[] args) {
        /**
         * foreach 实现
         */
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        functionDemo functionDemo = new functionDemo();
        functionDemo.test(list, System.out::println);
        /**
         * Supplier<T>举例
         */
        student student = new student();
        student.name = "123";
        Null<Object> Null = new Null<>();
        Object name = Null.of(student, () -> student.name);
        System.out.println(name.toString());

        /**
         * 高阶函数的使用(函数传参)
         * 对应数学情况
         * f(f(x))
         */
        //函数定义
        Function1 function = (o, predicate) -> {
            if (predicate.test(o)) {
                System.out.println(predicate.test(o));
            }
        };
        Object o = "";
        //内函数定义
        function.op(o, t -> o.equals(""));

        /**
         * 高阶函数的使用(函数返回)
         * 对应数学情况
         * f(x)=g(x)+g(x)
         */
        //函数定义
        Function2<Integer, Integer> function1 =(x,y,f)->f.apply(x)+f.apply(y);
        //内函数定义
        Integer op = function1.op(5, 10, (t) -> t + 1);
        System.out.println(op);
    }

    public void test(List list, FunctionInter functionInter) {
        for (Object o : list) {
            functionInter.foreach(o);
        }
    }

    static class student {
        public String name;
    }

}
