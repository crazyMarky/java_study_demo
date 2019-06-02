package com.jsd.function.functionInterDemo;

@FunctionalInterface
public interface FunctionInter<T> {
    /**
     * foreach函数式接口
     * @param t
     */
     void foreach(T t);
    /**
     * 有且只能有一个抽象接口，default和static是可以允许存在的，只有抽象的方法
     * 只允许一个
     */
    default void defMethod(){
        System.out.println("defMethod");
    }
    static void staMethod(){
        System.out.println("staMethod");
    }
    /**
     * 函数式接口(Functional Interface)就是一个有且仅有一个(除和Object中方法有相同签名的外)抽象方法
     * @param obj
     * @return
     */
    @Override
    abstract boolean equals(Object obj);

}
