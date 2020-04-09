package com.jsd.proxy.statics;

/**
 * 静态代理的接口
 */
public interface UserManager {

    public void addUser(String userId, String userName) ;

    public void delUser(String userId);

    public String findUser(String userId);
}
