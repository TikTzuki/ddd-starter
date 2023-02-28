package org.tiktzuki.dddstater.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.tiktzuki.dddstater.controller.OrderController;
import org.tiktzuki.dddstater.domains.order.service.OrderService;
import org.tiktzuki.dddstater.dtos.OrderDto;

@Controller
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> createOrder() {
        OrderDto order = orderService.createOrder();
        return ResponseEntity.ok(order);
    }
}
