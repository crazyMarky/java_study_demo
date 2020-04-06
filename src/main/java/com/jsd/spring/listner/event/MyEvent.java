package com.jsd.spring.listner.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by liaoh on 2020/1/8.
 */

public class MyEvent extends ApplicationEvent {

    public final static class onAdd extends MyEvent {
        public onAdd(Object source) {
            super(source);
        }
    }

    public final static class onDel extends MyEvent {
        public onDel(Object source) {
            super(source);
        }
    }

    public MyEvent(Object source) {
        super(source);
    }
}
