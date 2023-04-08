package org.dyson.dddstarter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.dyson.dddstarter.CreateOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderController {

    @Operation(
            tags = {"Order"},
            summary = "Tạo hóa đơn"
    )
    @PostMapping(value = "/orders")
    ResponseEntity<Object> createOrder(@RequestBody CreateOrderCommand createOrderCommand);
}
