package org.dyson.dddstarter.order;

import lombok.AllArgsConstructor;
import org.dyson.dddstarter.order.model.Order;
import org.springframework.stereotype.Component;
import org.dyson.dddstarter.order.model.OrderRepository;

@Component
@AllArgsConstructor
public class OrderFactory {
    OrderRepository orderRepository;

    public Order createOrder() {
        return orderRepository.save(new Order());
    }
}
