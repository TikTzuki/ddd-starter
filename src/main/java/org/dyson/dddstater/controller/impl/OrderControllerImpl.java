package org.dyson.dddstater.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dyson.dddstater.controller.OrderController;
import org.dyson.dddstater.domains.order.service.OrderService;
import org.dyson.dddstater.dtos.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

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
