package org.tiktzuki.dddstater.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.tiktzuki.dddstater.dtos.OrderDto;

public interface OrderController {

    @Operation(
            tags = {"Order"},
            summary = "Tạo hóa đơn"
    )
    @PostMapping(value = "/orders")
    ResponseEntity<OrderDto> createOrder();
}
