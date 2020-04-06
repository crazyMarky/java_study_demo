package com.jsd.test;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 泛型的擦除除了出现在类里面，也会出现在方法里面
 * 因为泛型的擦除会出现一些有趣的地方
 *
 * @author A001007008
 */
public class TMethod {
    public static void main(String[] args) throws ClassNotFoundException {
        //在这一步里面，getFirst()因为泛型擦除之后，返回的值是Object
        //编译器自动插入了Employee的强制类型转换
        Pair<Employee> buddies = new Pair<>(new Employee(), new Employee());
        Employee first = buddies.getFirst();

    }

    /**
     * 翻译泛型方法
     * 方法中泛型的擦除会带来两个复杂的问题
     */
}

class DateInterVal extends Pair<Date> {
    public DateInterVal(Date first, Date second) {
        super(first, second);
    }

    @Override
    public void setSecond(Date second) {
        if (second.compareTo(getFirst()) <= 0) {
            super.setSecond(second);
        }
    }

    /**
     * 目前为止都是正常的
     * 但是泛型擦除之后
     * class DateInterVal extends Pair{
     * public void setSecond(Date second){...}
     * }
     * 令人感到奇怪的是，存在另一个从Pair继承的setSecond()方法
     * 即setSecond(Object second)
     * 这显然是一个不同的方法，因为他有一个不同的参数- Object 而不是Date(多态)
     * 考虑下面的语句序列1.1
     */
    public static void main(String[] args) throws Exception {
        //运行一下代码，可以看到，在重写了Pair中的setSecond()方法之后
        //任然存在setSecond(Object)
        DateInterVal dateInterVal = new DateInterVal(new Date(), new Date());
        Class<? extends DateInterVal> aClass = dateInterVal.getClass();
        Method[] methods = aClass.getMethods();
        //对比试验
        Manager manager = new Manager();
        //当父类对象引用子类实例的时候，存在多态的情况下
        //不强制转换的情况下，是无法调用子类中独有的方法的
        Employee e = manager;
        //这语句无法通过编译
//        e.test(Data);

        //1.1
        Pair<Date> pair = dateInterVal;
        //与上面对比，当存在泛型擦除出现多态的情况下，
        //这里居然是可以通过编译的
        Date adate = new Date();
        pair.setSecond(adate);
        /**
         * 当类型擦除和多态发生冲突的时候，需要编译器在DateInterval类中生成
         * 一个桥方法(bridge method)
         * public void setSecond(Object second){
         * setSecond((Date) second);
         * }
         */
        //利用反射验证桥方法
        //aClass获取的是dateInterVal的Class
        //找到Class里面的setSecond(Object)方法
        //这个setSecond(Object)是存在的，但是直接用dateInterVal对象是无法获取到了
        Method setSecond = aClass.getMethod("setSecond", Object.class);
        //接着我们传入一个Pair<Date> pair的对象，使用这个对象调用setSecond这个方法
        //模拟父类对象应用子类方法的情况
        //在DateInterVal的setSecond(Date)中打上断点，可以发现，调用的是子类的setSecond(Date)的方法
        //原理就是上面所说的桥方法
        setSecond.invoke(pair, adate);
        /**
         *  Pair<Date> pair=dateInterVal;
         *  Date adate = new Date();
         *  pair.setSecond(adate);
         *  变量pair已经声明为类型Pair<Date>，并且这个类型只有一个setSecond(Object)(T被擦除为Object)
         *  虚拟机使用这个pair对象调用这个方法，这个对象是DateInterVal，因而会调用DateInterVal.setSecond(Object)方法
         *  这个方法是合成的桥方法。它调用DateInterVal.setSecond(Date)，这才是我们预想到的正常结果
         *
         * 假设DateInterVal覆盖了getSecond方法
         * 由于类型擦除也会存在两个方法
         *  Object getSecond()
         *  Date getSecond()
         *  在Java中的代码是不可以这样写的
         *  但是虚拟机上面可以(方法签名不一样)
         *
         *  注意，桥方法并非只存在泛型类型，一个方法覆盖另一个方法时可以
         *  指定一个更严格的返回类型(同上)
         *
         */

        /**
         * 类型擦除带来的一下泛型转换总结
         * 虚拟机中没有泛型，只有普通的类和方法
         * 所有的类型参数都用她们的限定类型替换
         * 桥方法被合成来保持多态
         * 为保证类型安全性，必要时插入强制转换
         */
    }

}
