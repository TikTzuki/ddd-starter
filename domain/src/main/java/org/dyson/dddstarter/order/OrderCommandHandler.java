package org.dyson.dddstarter.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.dyson.dddstarter.CreateOrderCommand;
import org.dyson.dddstarter.order.model.Order;
import org.dyson.dddstarter.order.model.OrderRepository;
import org.dyson.dddstarter.order.model.OrderShipped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCommandHandler {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final CommandGateway commandGateway;
    private final EventBus eventBus;


    @CommandHandler
    public Object handle(CreateOrderCommand command) {
        Order order = orderFactory.createOrder();
        log.info("--> created order id {}", order.getId());
        order.recalculateTotals();
        order.ship();
        orderRepository.save(order);
        return order.getId();
    }

}
