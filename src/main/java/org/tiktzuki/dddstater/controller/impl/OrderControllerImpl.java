package org.tiktzuki.dddstater.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.tiktzuki.dddstater.controller.OrderController;
import org.tiktzuki.dddstater.domains.order.service.OrderService;
import org.tiktzuki.dddstater.dtos.OrderDto;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderDto> createOrder() {
        OrderDto order = orderService.createOrder();
        log.info("response http");
        return ResponseEntity.ok(order);
    }
}
