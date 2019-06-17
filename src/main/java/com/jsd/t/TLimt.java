package com.jsd.t;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 下面阐述泛型设计的一些约束和规范
 *
 * @author A001007008
 */
public class TLimt {


    public static void main(String[] args) {
        /**
         * 第一点，不能使用基本参数实例化参数类型
         * 因此没有Pair<double>只有Pair<Double>
         * 因为类型擦除之后只有只含有Object，Object无法储存基本类型
         */
//        Pair<double> d= new Pair<>();

        /**
         * 第二点，运行时检查类型查询只适用于原始类型
         * 理由很简单，类型擦除之后，返回的总是原始类型
         */
//        Pair<String> a;
//        if (a instanceof Pair<String>)

        /**
         * 第三点，不能创建参数化的类型
         * 类型擦除之后，数组会记住这个数组只能储存Object类型的元素
         * 当试图储存其他元素类型的元素就会抛出异常
         * 这一点是人为的不允许创建
         * 变量的声明是合法的
         *
         */
//        Pair<String>[] pairs = new Pair<String>[10];
        Pair<String>[] pairs = new Pair[10];
        Object[] objects = pairs;
        //存在类型的错误储存
//        objects[0]=new Pair<Employee>();

        /**
         * 第四点 Varargs警告
         * java中不支持泛型类的数组
         */
        Collection<Pair<String>> table = new ArrayList<>();
        Pair<String> pair1 = new Pair<>();
        Pair<String> pair2 = new Pair<>();
        //为了调用这个方法，Java虚拟机必须建立一个Pair<String>的数组，显然违反了前面的规则
        //但这里智慧得到一个警告，而不是一个错误

        addAll(table, pair1, pair2);

        /**
         * 第五点，不能实例化类型变量
         * new T(....),new T[...],T.class是非法的
         * {@link Pair3}
         * 不能构造一个泛型数组
         * public static <T extends Comparable> T[] minmax(T[] a) {T[] mm = new T[2]}
         * 类型擦除会让这个方法永远构造会构造Object[2]的数组
         * (存疑，类型擦除之后应该是Comparable类型的)
         * 如果数组仅仅作为一个类的私有实用域，就可以将数组声明为Object[]，并且在获取元素时进行类型
         * 转换。
         * 贴一段代码来理解这个情况
         * {@code
         *   public class  ArrayList<E>{
         *     private Object[] elements;
         *
         *     public E getElements(int n) {
         *         return (E)elements[n];
         *     }
         *
         *     public void setElements(int n,E e) {
         *         elements[n]=e;
         *     }
         * }}
         * 而实际情况下，实现要没那么清晰
         * {@code public class  ArrayList<E>{
         *    private E[] elements;
         *    public ArrayList(){
         *        elements =E[] new Objcet=[10];
         *    }
         *     }
         * }
         * 这里面E[]强制转换只是一个假象，让类型擦除机制无法识别
         *
         * public static <T extends Comparable> T[] minmax(T[] a) {
         * Object[] mm = new Object[2];
         * return (T[])mm;
         * }
         *  调用用String[] ss =minmax("","","");
         *  编译的时候不会有任何的警告，当Object[]引用付给String[]变量时，将会抛出类型转换的异常
         *  在这种情况下，可用反射来实现
         *  public static <T extends Comparable> T[] minmax(T... a) {
         *    T[] mm=(T[])Array.newInstance(a.getClass.getComponentTyoe(),2);
         *  }
         */
        Pair3.makePair(String.class);

        /**
         * 第六点，泛型类的静态上下问中类型变量无效
         * 不能再静态域或方法中引入类型变量
         */

        /**
         * 第七点，不能抛出或捕获泛型类实例
         * 既不能抛出也不能捕获泛型类对象，甚至泛型类拓展Throwable都是不合法的
         */

        /**
         * 第八点，注意擦除后的冲突
         * {@link Pair3}
         */

    }

    //可以使用@SafeVarargs来抑制这个警告
    @SafeVarargs
    public static <T> T addAll(Collection<T> collection, T... ts) {
        for (T t :
                ts) {
            collection.add(t);
        }
        return null;
    }
}
