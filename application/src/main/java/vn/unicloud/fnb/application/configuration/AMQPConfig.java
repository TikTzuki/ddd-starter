package vn.unicloud.fnb.application.configuration;

import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.amqp.AMQPProperties;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AMQPConfig {
    private final AMQPProperties amqpProperties;

    @Bean
    TopicExchange eventsExchange() {
        return new TopicExchange(amqpProperties.getExchange());
    }

    @Bean
    Queue eventsQueue() {
        return new Queue(AxonConfig.QUEUE_NAME);
    }

    @Bean
    Binding eventsBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("#");
    }
}
