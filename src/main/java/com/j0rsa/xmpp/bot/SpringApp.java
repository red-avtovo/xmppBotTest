package com.j0rsa.xmpp.bot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class SpringApp {
    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml","xmpp-outbound-config.xml");
        TimeUnit.MINUTES.sleep(5);
    }
}
