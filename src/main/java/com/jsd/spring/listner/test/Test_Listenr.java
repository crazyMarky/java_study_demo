package com.jsd.spring.listner.test;

import com.jsd.spring.listner.config.MainConfig;
import com.jsd.spring.listner.event.MyEvent;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;

import java.io.IOException;

/**
 * Created by liaoh on 2020/1/8.
 */

public class Test_Listenr {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        applicationContext.publishEvent(new MyEvent("11"));
        applicationContext.publishEvent(new MyEvent.onAdd("12"));
        applicationContext.publishEvent(new MyEvent.onDel("134"));
    }
}
