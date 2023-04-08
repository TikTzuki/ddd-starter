package org.dyson.dddstarter.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.dyson.dddstarter.CreateOrderCommand;
import org.dyson.dddstarter.order.model.Order;
import org.dyson.dddstarter.order.model.OrderRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCommandHandler {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;

    @CommandHandler
    public Object handle(CreateOrderCommand command) {
        Order order = orderFactory.createOrder();
        log.info("--> created order id {}", order.getId());
        order.recalculateTotals();
        order.ship();
        orderRepository.save(order);
        return "1";
    }
}
