package com.jsd.jvm.classload;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(Parant2.str);
    }
}

class Parant2{
    public static String str = "hellow world ";

    static {
        System.out.println("Parant2 static block");
    }
}