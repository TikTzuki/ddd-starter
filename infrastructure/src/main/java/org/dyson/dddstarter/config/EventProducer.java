package org.dyson.dddstarter.config;

import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.amqp.AMQPProperties;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventProducer {
    private final AMQPProperties amqpProperties;

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(amqpProperties.getExchange());
    }

//    @Bean
//    public Queue queue() {
//        return new Queue("orderServiceQueue");
//    }
//
//    @Bean
//    public Binding binding(Queue queue, Exchange eventExchange) {
//        return BindingBuilder
//                .bind(queue)
//                .to(eventExchange)
//                .with("order.*").noargs();
//    }
//
//    @Bean
//    public EventConsumer eventConsumer() {
//        return new EventConsumer();
//    }
}

