package com.jsd.proxy.statics;

/**
 * 静态代理
 */
public class Main {
    public static void main(String[] args) {
        UserManager implProxy = new UserManagerImplProxy(new UserManagerImpl());
        implProxy.findUser("121");
    }
}
