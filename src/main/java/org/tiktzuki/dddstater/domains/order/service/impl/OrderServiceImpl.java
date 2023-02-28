package org.tiktzuki.dddstater.domains.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tiktzuki.dddstater.domains.model.order.Order;
import org.tiktzuki.dddstater.domains.model.order.OrderRepository;
import org.tiktzuki.dddstater.domains.order.service.OrderService;
import org.tiktzuki.dddstater.dtos.OrderDto;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderDto createOrder() {
        Order order = new Order();
        order.ship();
        orderRepository.save(order);
        return OrderDto.builder()
                .orderId(order.getId())
                .billingName(order.getBillingName())
                .shippingName(order.getShippingName())
                .build();
    }
}
