package vn.unicloud.fnb.configuration;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.amqp.AMQPProperties;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.springboot.EventProcessorProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AxonConfig {
    public static final String QUEUE_NAME = "dddQueue";
    private final AMQPProperties amqpProperties;
    /**
     * The SpringAMQPMessageSource allows event processors to read messages from a queue instead of the event store or
     * event bus. It acts as an adapter between Spring AMQP and the SubscribableMessageSource needed by these processors.
     *
     * @param messageConverter Converter to/from AMQP Messages to/from Axon Messages.
     */
    @Bean
    SpringAMQPMessageSource amqpMessageSource(AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = QUEUE_NAME)
            public void onMessage(Message message, Channel channel) {
                super.onMessage(message, channel);
            }

        };
    }

    @Bean
    TopicExchange eventsExchange() {
        return new TopicExchange(amqpProperties.getExchange());
    }

    @Bean
    Queue eventsQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    Binding eventsBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("#");
    }

}