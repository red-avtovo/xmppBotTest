package com.j0rsa.xmpp.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * @author red
 * @since 18.07.15
 */
@Component
public class BotService{

    private static final Logger logger = LoggerFactory.getLogger(BotService.class);

    private String selfLogin;

    public BotService() throws IOException {
        Resource resource = new ClassPathResource("/xmpp.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        selfLogin = props.getProperty("user.login");

    }

    public Message answer(Message message) {
        MessageHeaders headers = message.getHeaders();

        String from = (String) headers.get("xmpp_from");
        String payload = (String) message.getPayload();

        if (from != null && from.startsWith(selfLogin)) {
            return null;
        }
        logger.info("Received a message from: {} with payload: {}", from, payload);

        return MessageBuilder
                .withPayload("Received: " + payload)
                .setHeader("xmpp_to",message.getHeaders().get("xmpp_from"))
                .build();
    }

}
