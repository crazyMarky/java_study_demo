package com.jsd.t;

import com.jsd.asyncUtil.AsyncUtils;

public class AsyncUtilsTest {
    public static void main(String[] args) {
        AsyncUtils.run(() -> System.out.println("AsyncUtils"));
        System.out.println("main");
    }
}
