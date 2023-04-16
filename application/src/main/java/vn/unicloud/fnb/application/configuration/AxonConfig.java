package vn.unicloud.fnb.application.configuration;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AxonConfig {
    public static final String QUEUE_NAME = "dddQueue";

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



}