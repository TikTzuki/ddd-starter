package org.dyson.dddstarter.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.dyson.core.model.DomainEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EventProducer {
    private final EventGateway eventGateway;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(DomainEvent e) {
        eventGateway.publish(e);
        log.debug("event gateway pushed {}", e);
    }
//    private final AMQPProperties amqpProperties;
//
//    @Bean
//    public TopicExchange eventExchange() {
//        return new TopicExchange(amqpProperties.getExchange());
//    }

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

