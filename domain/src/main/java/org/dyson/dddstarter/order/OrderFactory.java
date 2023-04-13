package org.dyson.dddstarter.order;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.dyson.dddstarter.order.model.Order;
import org.dyson.dddstarter.order.model.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFactory {
    private final OrderRepository orderRepository;

    public Order createOrder() {
        factory().createAggregateRoot()
        return orderRepository.save(new Order());
    }
    @Bean
    SpringPrototypeAggregateFactory<Order> factory(){
        return new SpringPrototypeAggregateFactory<>("orderEntity");
    }
}
