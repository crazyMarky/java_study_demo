package com.jsd.spring.listner.listner;

import com.jsd.spring.listner.event.MyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * Created by liaoh on 2020/1/8.
 */
@Component
public class MyApplicationListnerImpl extends MyApplicationListner {


    @Override
    void onAdd(MyEvent event) {
        System.out.println("onAdd:"+event);
    }

    @Override
    void onDel(MyEvent event) {
        System.out.println("onDel"+event);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MyEvent.onDel){
            onDel((MyEvent.onDel)event);
        }else if (event instanceof MyEvent.onAdd){
            onAdd((MyEvent.onAdd)event);
        }

    }
}
