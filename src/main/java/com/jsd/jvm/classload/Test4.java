package com.jsd.jvm.classload;

/**
 * 对于数组实例来说，其类型是由JVM在运行期间动态生成的，表示为[Lcom.jsd.jvm.classload.Parent4这种形式
 * 动态生成的类型，其父类型就是Object
 * 对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型。
 *
 * 助记符：
 * annewarray：表示创建一个引用类型的数组，并将其引用值压入栈顶
 * newarray：表示创建一个指定的原始类型的数组，并将其压入栈顶。
 */
public class Test4 {
    public static void main(String[] args) {
        //Parent4 parent4 = new Parent4();

        Parent4[] parent4s = new Parent4[1];
        System.out.println(parent4s.getClass());
        System.out.println(parent4s.getClass().getSuperclass());

        Parent4[][] parent4ss = new Parent4[1][1];
        System.out.println(parent4ss.getClass());
        System.out.println(parent4ss.getClass().getSuperclass());

        System.out.println("===============");

        int[] ints =  new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());


        char[] chars =  new char[1];
        System.out.println(chars.getClass());
        System.out.println(chars.getClass().getSuperclass());


        boolean[] booleans =  new boolean[1];
        System.out.println(booleans.getClass());
        System.out.println(booleans.getClass().getSuperclass());


        short[] shorts =  new short[1];
        System.out.println(shorts.getClass());
        System.out.println(shorts.getClass().getSuperclass());

        byte[] bytes =  new byte[1];
        System.out.println(bytes.getClass());
        System.out.println(bytes.getClass().getSuperclass());
    }
}

class Parent4{
    static {
        System.out.println("Parent4 static code");
    }
}