package org.dyson.dddstater.domains.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dyson.dddstater.domains.order.OrderFactory;
import org.dyson.dddstater.domains.order.model.Order;
import org.dyson.dddstater.domains.order.model.OrderRepository;
import org.dyson.dddstater.domains.order.service.OrderService;
import org.dyson.dddstater.dtos.OrderDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public OrderDto createOrder() {
        Order order = orderFactory.createOrder();
        log.info("--> created order id {}", order.getId());
        order.recalculateTotals();
        order.ship();
        orderRepository.save(order);
        return OrderDto.builder()
                .orderId(order.getId())
                .billingName(order.getBillingName())
                .shippingName(order.getShippingName())
                .build();
    }
}
