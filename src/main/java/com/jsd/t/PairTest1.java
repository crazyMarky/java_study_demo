package com.jsd.t;

public class PairTest1 {

    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max =" + mm.getSecond());

        /**
         * 当调用一个泛型方法的时候，在方法名前的尖括号中放入具体类型
         *
         * 但在大多数情况下，方法调用中可以省略<String>参数类型。编译器有足够的信息能推断出所调用的
         * 方法
         */
        ArrayAlg.<String>getMiddle("John","Q.","Public");
        /**
         * 但是偶尔会出现错误,如下
         * (请注释掉下列语句查看编译错误类型)
         */
//        double middle = ArrayAlg.getMiddle(3.14, 1279, 0);
        /**
         * 简单来说，编译器将会自动打包参数为1个Double，和2个Integer对象，而后寻找
         * 这些类的共同超类，事实上找到了Number & Comparable
         * 当T表示的类型不一致的情况下，编译器会往上找各种类型的共同超类
         * 并要求返回值为超类之一
         */
        Number middle1 = ArrayAlg.getMiddle(3.14, 1279, 0);
        Double middle2 = ArrayAlg.getMiddle(3.14, 1279.0, 0.0);
    }
}

class ArrayAlg {
    /**
     * 基于前面定义的Pair类来定义一个方法
     * 方法返回一个Pair<>
     * 里面包含了传递的数组中的最大值，最小值
     *
     * @param a
     * @return
     */
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0)
            return null;
        String min = a[0];
        String max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
            if (max.compareTo(a[i]) < 0)
                max = a[i];
        }
        return new Pair<>(min, max);
    }

    /**
     * 泛型方法并非只能在泛型里面进行实现
     * 泛型方法可以定义在普通类里面，也可以定义在泛型类中
     * (在普通类的情况下)
     * 注意，类型变量放在修饰符的后面，放在类型的前面
     *
     * @param a
     * @param <T>
     * @return
     */
    public static <T> T getMiddle(T... a) {
      return a[a.length/2];
    }

    /**
     *一个需要计算数组中最小元素的
     * 但是compareTo是要实现了Comparable接口的类才存在这个方法
     * 这种时候我们需要对这个T进行限制
     * （以下代码不能通过编译）
     *
     */
//    public static <T> T min(T[] a) {
//        if (a == null || a.length == 0)
//            return null;
//        T min = a[0];
//        for (int i = 0; i < a.length; i++) {
//            if (min.compareTo(a[i]) > 0)
//                min = a[i];
//            if (max.compareTo(a[i]) < 0)
//                max = a[i];
//        }
//        return new Pair<>(min, max);
//    }

    /**
     * 我们可以使用extends来进行限制 T必须是实现了Comparable接口的类
     * 注意这个限制和普通的类标准是一个的，普通的类只能实现一个类，多个接口
     * 同样，在限制中可以这样的实现,使用&分隔符分隔
     * T extends Comparable&Serializable
     * 如果用一个类进行限定，它必须是限定类型的第一个
     *
     * @param a
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T min(T[] a) {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
        }
        return min;
    }
}