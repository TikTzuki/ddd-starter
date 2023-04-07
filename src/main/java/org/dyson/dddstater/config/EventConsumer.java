package org.dyson.dddstater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class EventConsumer {
    @RabbitListener(queues = "orderServiceQueue")
    public void receive(String message) {
        log.info("Received message '{}'", message);
    }
}
