package org.dyson.dddstater.domains.order;

import lombok.AllArgsConstructor;
import org.dyson.dddstater.domains.order.model.Order;
import org.springframework.stereotype.Component;
import org.dyson.dddstater.domains.order.model.OrderRepository;

@Component
@AllArgsConstructor
public class OrderFactory {
    OrderRepository orderRepository;

    public Order createOrder() {
        return orderRepository.save(new Order());
    }
}
