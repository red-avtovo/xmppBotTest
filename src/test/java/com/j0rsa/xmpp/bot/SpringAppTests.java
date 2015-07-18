package com.j0rsa.xmpp.bot;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringAppTests {

    @Autowired
    MessageChannel incomingMessagesChannel;

    @Autowired
    QueueChannel testQueueChannel;

    @Test
    public void testSayHello() {
        String text = "test message";
        Message<String> message = MessageBuilder.withPayload(text).build();
        incomingMessagesChannel.send(message);
        Assert.assertEquals(text, testChannel.receive().getPayload());
    }
}
