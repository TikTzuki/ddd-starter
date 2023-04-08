//package org.dyson.dddstarter.order;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.dyson.dddstarter.order.model.OrderShipped;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class OrderProducer {
//    @Autowired
//    private final RabbitTemplate rabbitTemplate;
//    @Autowired
//    private final TopicExchange eventExchange;
//
//    @EventListener
//    public void onOrderShipped(OrderShipped event) throws JsonProcessingException {
//        log.info("---> rabbit publish event: {}", event);
//        var mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String eventContent = mapper.writeValueAsString(event);
//        rabbitTemplate.convertAndSend(eventExchange.getName(), "order.shipped", eventContent);
//        log.info("---> rabbit published: {}", eventContent);
//    }
//}
