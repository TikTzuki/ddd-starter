package org.tiktzuki.dddstater.domains.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tiktzuki.dddstater.domains.order.OrderFactory;
import org.tiktzuki.dddstater.domains.order.model.Order;
import org.tiktzuki.dddstater.domains.order.model.OrderRepository;
import org.tiktzuki.dddstater.domains.order.service.OrderService;
import org.tiktzuki.dddstater.dtos.OrderDto;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public OrderDto createOrder() {
        Order order = orderFactory.createOrder();
        order.ship();
        orderRepository.save(order);
        return OrderDto.builder()
                .orderId(order.getId())
                .billingName(order.getBillingName())
                .shippingName(order.getShippingName())
                .build();
    }
}
