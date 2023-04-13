package org.dyson.dddstarter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.dyson.dddstarter.CreateOrderCommand;
import org.dyson.dddstarter.UpdateOrderCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
public interface OrderController {

    @Operation(
        tags = {"Order"},
        summary = "Lấy đơn hàng"
    )
    @PostMapping
    ResponseEntity<Object> getOrder();

    @Operation(
        tags = {"Order"},
        summary = "Tạo đơn hàng"
    )
    @PostMapping
    ResponseEntity<Object> createOrder(@RequestBody CreateOrderCommand command);

    @Operation(
        tags = {"Order"},
        summary = "Cập nhật đơn hàng"
    )
    @PutMapping
    ResponseEntity<Object> updateOrder(@RequestBody UpdateOrderCommand command);


    @Operation(
        tags = {"Order"},
        summary = "Xóa đơn hàng"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable("id") Long orderId);

}
