package com.jsd.proxy.jdkdynamic;

import com.jsd.proxy.statics.UserManager;
import com.jsd.proxy.statics.UserManagerImpl;

/**
 * JDK动态代理，代理任何一个实现类
 */
public class Main {
    public static void main(String[] args) {
        LogHandler logHandler = new LogHandler();
        UserManager userManager = (UserManager)logHandler.newProxyInstance(new UserManagerImpl());
        userManager.delUser("12");
    }
}
