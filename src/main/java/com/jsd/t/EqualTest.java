package com.jsd.t;

public class EqualTest {
    public static void main(String[] args) {
        String s = new String("ABC");
        String s1 = "ABCD";
        System.out.println(s.hashCode());
        System.out.println(s1.hashCode());
    }
}
