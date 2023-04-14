package vn.unicloud.fnb.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.fnb.dto.CreateOrderCommand;
import vn.unicloud.fnb.dto.UpdateOrderCommand;

import java.util.Optional;

@RequestMapping("/orders")
public interface OrderController {

    @Operation(
        tags = {"Order"},
        summary = "Lấy đơn hàng"
    )
    @GetMapping({"/{id}"})
    ResponseEntity<Object> get(@PathVariable Optional<Long> id, @ParameterObject Pageable pageable);

    @Operation(
        tags = {"Order"},
        summary = "Tạo đơn hàng"
    )
    @PostMapping
    ResponseEntity<Object> create(@RequestBody CreateOrderCommand command);

    @Operation(
        tags = {"Order"},
        summary = "Cập nhật đơn hàng"
    )
    @PutMapping
    ResponseEntity<Object> update(@RequestBody UpdateOrderCommand command);


    @Operation(
        tags = {"Order"},
        summary = "Xóa đơn hàng"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long orderId);

}
