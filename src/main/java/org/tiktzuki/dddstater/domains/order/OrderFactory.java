package org.tiktzuki.dddstater.domains.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.tiktzuki.dddstater.domains.order.model.Order;
import org.tiktzuki.dddstater.domains.order.model.OrderRepository;

@Component
@AllArgsConstructor
public class OrderFactory {
    OrderRepository orderRepository;

    public Order createOrder() {
        return new Order();
    }
}
