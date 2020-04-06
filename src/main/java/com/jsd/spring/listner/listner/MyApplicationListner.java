package com.jsd.spring.listner.listner;

import com.jsd.spring.listner.event.MyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by liaoh on 2020/1/8.
 */


public  abstract class MyApplicationListner implements ApplicationListener {

    abstract void onAdd(MyEvent event);

    abstract void onDel(MyEvent event);

}
